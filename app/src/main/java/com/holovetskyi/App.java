package com.holovetskyi;

import com.holovetskyi.car.Car;
import com.holovetskyi.car.CarValidator;
import com.holovetskyi.car.type.Color;
import com.holovetskyi.json.CarsJsonConverter;
import com.holovetskyi.repo.CarRepo;
import com.holovetskyi.repo.statistics.StatisticsCollector;
import com.holovetskyi.validator.Validator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;

import static com.holovetskyi.car.type.Color.BLACK;
import static com.holovetskyi.repo.type.Direction.ASCENDING;
import static com.holovetskyi.repo.type.Direction.DESCENDING;
import static com.holovetskyi.repo.type.ModelType.BMW;
import static com.holovetskyi.repo.type.SortType.COLOR;
import static com.holovetskyi.repo.type.SortType.MILEAGE;
import static com.holovetskyi.repo.type.SortType.MODEL;
import static com.holovetskyi.repo.type.SortType.PRICE;

public class App {

    private static final Log LOG = LogFactory.getLog(App.class.getName());
    public static void main(String[] args) {



        /**------------------------DATA FOR INITIALIZATION------------------------------**/

        final String carJsonFilename = "car.json";

        List<Car> cars = List.of(
                Car.builder()
                        .model("AUDI")
                        .price(new BigDecimal("1200"))
                        .mileage(1500D)
                        .color(BLACK)
                        .components(List.of("DPS", "ALLOY WHEELS"))
                        .build(),
                Car.builder()
                        .model("BMW")
                        .price(new BigDecimal("1250"))
                        .mileage(1300D)
                        .color(BLACK)
                        .components(List.of("DPS"))
                        .build());
        /**---------------------------------THE END-----------------------------------**/

        /**----------------------------------VALIDATE AND CREATE JSON FORMAT----------------------------------**/
        CarsJsonConverter carJsonConverter = new CarsJsonConverter(carJsonFilename);
        carJsonConverter.toJson(
                cars.stream()
                        .filter(car -> Validator.validate(new CarValidator(), car))
                        .toList());
        /**---------------------------------THE END-----------------------------------**/


        /**------------------------------INITIALIZATION CarRepo WITH FROM JSON------------------------**/
        CarRepo carRepo = new CarRepo(carJsonFilename);
        /**---------------------------------THE END-----------------------------------**/

        /**SORTING*/
        System.out.println("-----------------SORT BY MODEL-----------------");
        List<Car> models = carRepo.sort(MODEL, DESCENDING);
        models.forEach(System.out::println);

        System.out.println("\n----------------SORT BY COLOR-----------------");
        List<Car> colors = carRepo.sort(COLOR, ASCENDING);
        colors.forEach(System.out::println);

        System.out.println("\n-----------------SORT BY PRICE-----------------");
        List<Car> price = carRepo.sort(PRICE, DESCENDING);
        price.forEach(System.out::println);

        System.out.println("\n-----------------SORT BY MILEAGE-----------------");
        String sortByMileage = "mileage";
        List<Car> mileages = carRepo.sort(MILEAGE, DESCENDING);
        mileages.forEach(System.out::println);

        /**MILEAGE WITH THE GREATER VALUE*/
        System.out.println("\n-----------------MILEAGE WITH THE GREATER VALUE-----------------");
        double mileage = 1200;
        List<Car> carMileage = carRepo.mileageWithTheHighestValue(mileage);
        carMileage.forEach(System.out::println);


        /**GET CARS OF A SPECIFIC COLOR**/
        System.out.println("\n-----------------GET CARS OF A SPECIFIC COLOR-----------------");
        Map<Color, Long> carQuantity = carRepo.countByColor();
        carQuantity.forEach((k, v) -> System.out.printf(" Color: %s \n Get car by color: %s\n", k, v));


        /**EXPENSIVE CAR**/
        System.out.println("\n---------------FIND THE MOST EXPENSIVE CAR WITH A SPECIFIC MODEL NAME-------------");
        Map<String, List<Car>> expensiveCarByModel = carRepo.getMostExpensiveCarPerModel(BMW);
        expensiveCarByModel.forEach((k, v) -> System.out.printf(" Model: %s \n Get car by model: %s\n", k, v));

        /**CAR STATISTICS MILEAGE*/
        System.out.println("\n---------------CAR STATISTICS MILEAGE-------------");
        DoubleSummaryStatistics statistics = carRepo.getStatisticsMileage();
        System.out.printf("Statistics mileage: Min: %s, Max: %s, Average: %s \n", statistics.getMin(),
                statistics.getMax(), statistics.getAverage());


        /**CAR STATISTICS PRICE*/
        System.out.println("\n---------------CAR STATISTICS PRICE-------------");
        StatisticsCollector.Statistics<BigDecimal> statisticsPrice = carRepo.getStatisticsPrice();
        System.out.println("Statistics price: " + statisticsPrice);

        /**THE MOST EXPENSIVE CAR*/
        System.out.println("\n---------------THE MOST EXPENSIVE CAR-------------");
        List<Car> expensiveCar = carRepo.getExpensiveCar();
        System.out.println(expensiveCar);

        /**AN ALPHABETICALLY SORTED COLLECTION OF COMPONENTS*/
        System.out.println("\n---------------AN ALPHABETICALLY SORTED COLLECTION OF COMPONENTS-------------");
        List<Car> sortedComponents = carRepo.sortComponents();
        System.out.println(sortedComponents);

        /**GROUP BY COMPONENT KEY*/
        System.out.println("\n---------------KEY COMPONENT-------------");
        Map<String, List<Car>> mapKeyComponent = carRepo.groupByComponents();
        mapKeyComponent.forEach((k,v) -> System.out.printf(" Model: %s \n Get car by model: %s\n", k, v));

        /**FILTERING BY PRICE*/
        System.out.println("\n---------------FILTERING BY PRICE-------------");
        BigDecimal min = new BigDecimal("1200");
        BigDecimal max = new BigDecimal("1300");
        List<Car> filterByPrice = carRepo.getCarsByPriceBetween(min, max);
        System.out.println(filterByPrice);
    }
}
