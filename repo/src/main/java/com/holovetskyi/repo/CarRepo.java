package com.holovetskyi.repo;

import com.holovetskyi.car.Car;
import com.holovetskyi.car.CarValidator;
import com.holovetskyi.car.type.Color;
import com.holovetskyi.json.CarsJsonConverter;
import com.holovetskyi.repo.exception.CarRepoException;
import com.holovetskyi.repo.statistics.StatisticsCollector;
import com.holovetskyi.repo.statistics.StatisticsCollector.Statistics;
import com.holovetskyi.repo.type.Direction;
import com.holovetskyi.repo.type.ModelType;
import com.holovetskyi.repo.type.SortType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.holovetskyi.car.CarUtils.compareByColor;
import static com.holovetskyi.car.CarUtils.compareByMileage;
import static com.holovetskyi.car.CarUtils.compareByModel;
import static com.holovetskyi.car.CarUtils.compareByPrice;
import static com.holovetskyi.car.CarUtils.toColor;
import static com.holovetskyi.car.CarUtils.toComponentsAsStream;
import static com.holovetskyi.car.CarUtils.toMileage;
import static com.holovetskyi.car.CarUtils.toModel;
import static com.holovetskyi.car.CarUtils.toPrice;
import static com.holovetskyi.repo.type.Direction.ASCENDING;
import static com.holovetskyi.validator.Validator.validate;
import static java.util.Map.Entry.comparingByKey;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public class CarRepo {

    private static final Log LOG = LogFactory.getLog(CarRepo.class.getName());
    private final List<Car> cars;

    public CarRepo(String filename) {
        this.cars = init(filename);
    }

    private static List<Car> init(String filename) {
        var carValidator = new CarValidator();
        return new CarsJsonConverter(filename)
                .fromJson()
                .orElseThrow(() -> new CarRepoException("Cannot parse json data from file %s".formatted(filename)))
                .stream()
                .filter(car -> validate(carValidator, car))
                .toList();
    }

    public List<Car> sort(SortType sortType, Direction direction) {
        if (sortType == null) {
            throw new CarRepoException("Sort type object is null");
        }

        if (direction == null) {
            throw new CarRepoException("Direction type object is null");
        }

        return switch (sortType) {
            case COLOR -> sortCarsByParameter(compareByColor, direction);
            case MILEAGE -> sortCarsByParameter(compareByMileage, direction);
            case MODEL -> sortCarsByParameter(compareByModel, direction);
            case PRICE -> sortCarsByParameter(compareByPrice, direction);
        };
    }

    public List<Car> mileageWithTheHighestValue(double mileage) {
        if (mileage <= 0) {
            throw new CarRepoException("Mileage must be positive");
        }

        return cars.stream()
                .filter(c -> c.hasMileageGreaterThan(mileage))
                .collect(Collectors.toList());
    }

    public Map<Color, Long> countByColor() {
        return cars
                .stream()
                .collect(groupingBy(toColor, counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, Long::sum, LinkedHashMap::new));
    }

    public Map<String, List<Car>> getMostExpensiveCarPerModel(ModelType model) {
        return cars
                .stream()
                .filter(car -> car.hasModelGivenInParameter(model.toString()))
                .collect(groupingBy(
                        toModel,
                        collectingAndThen(
                                groupingBy(toPrice),
                                groupedByPrice -> groupedByPrice
                                        .entrySet()
                                        .stream()
                                        .max(comparingByKey())
                                        .map(Map.Entry::getValue)
                                        .orElseThrow()
                        )));
    }


    public DoubleSummaryStatistics getStatisticsMileage() {
        return cars.stream()
                .mapToDouble(toMileage)
                .summaryStatistics();
    }

    public Statistics<BigDecimal> getStatisticsPrice() {
        return cars.stream()
                .map(toPrice)
                .collect(new StatisticsCollector<>(BigDecimal::compareTo, BigDecimal::add));
    }

    public List<Car> getExpensiveCar() {
        return cars.stream()
                .collect(groupingBy(toPrice))
                .entrySet()
                .stream()
                .max(comparingByKey())
                .orElseThrow()
                .getValue();
    }

    public List<Car> sortComponents() {
        return cars.stream()
                .map(Car::withSortedComponents)
                .toList();
    }

    public Map<String, List<Car>> groupByComponents() {
        return cars
                .stream()
                .flatMap(toComponentsAsStream)
                .distinct()
                .collect(Collectors.toMap(
                        identity(), component -> cars.stream().filter(car -> car.containsComponent(component)).toList()))
                .entrySet()
                .stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
    }

    public List<Car> getCarsByPriceBetween(BigDecimal priceFrom, BigDecimal priceTo) {
        return cars.stream()
                .filter(car -> car.hasPriceBetween(priceFrom, priceTo))
                .sorted(compareByModel)
                .toList();
    }

    private List<Car> sortCarsByParameter(Comparator<Car> compareByParameter, Direction direction) {
        return direction.equals(ASCENDING) ?
                cars.stream()
                        .sorted(compareByParameter)
                        .toList() :
                cars.stream()
                        .sorted(compareByParameter.reversed())
                        .toList();
    }
}
