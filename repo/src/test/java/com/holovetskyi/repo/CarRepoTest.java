package com.holovetskyi.repo;

import com.holovetskyi.car.Car;
import com.holovetskyi.repo.type.SortType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static com.holovetskyi.repo.SortAssertion.*;
import static com.holovetskyi.repo.type.Direction.ASCENDING;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

class CarRepoTest implements SimpleFilename {

    private final CarRepo carRepo = new CarRepo(getFilename());

    @ParameterizedTest
    @EnumSource(SortType.class)
    void shouldReturn_sortedList_byType_inAscendingDirection(SortType sortType) {
        // given

        // when
        List<Car> sortResult = carRepo.sort(sortType, ASCENDING);
        // then
        than(sortResult).hasBeenSortedByType(sortType);
    }

//    @Test
//    void shouldReturnSortedListByColorInDescendingDirection() {
//        // given
//        String color = "color";
//        // when
//        List<Car> cars = build().sortCars(color, Direction.DESCENDING);
//        // then
//        assertEquals(Color.WHITE, cars.get(0).getColor());
//    }
//
//    @Test
//    void shouldReturnSortedListByPriceInAscendingDirection() {
//        // given
//        String price = "price";
//        // when
//        List<Car> cars = build().sortCars(price, Direction.ASCENDING);
//        // then
//        assertEquals(BigDecimal.valueOf(1500), cars.get(0).getPrice());
//    }
//
//    @Test
//    void shouldReturnSortedListByMillageInDescendingDirection() {
//        // given
//        String mileage = "mileage";
//        // when
//        List<Car> cars = build().sortCars(mileage, Direction.DESCENDING);
//        // then
//        assertEquals(BigDecimal.valueOf(2500), cars.get(0).getMileage());
//    }
//
//    @Test
//    void should_return_a_mileage_greater_then_that_specified_in_the_argument() {
//        // given
//        BigDecimal mileage = new BigDecimal("2400");
//        // when
//        List<Car> cars = build().mileageWithTheHighestValue(mileage);
//        // then
//        assertThat(cars)
//                .isNotEmpty()
//                .hasSize(1);
//    }
//
//    @Test
//    void shouldReturnEmptyList() {
//        // given
//        BigDecimal mileage = new BigDecimal("2600");
//        // when
//        List<Car> cars = build().mileageWithTheHighestValue(mileage);
//        // then
//        String[] s = {};
//        assertArrayEquals(s, cars.toArray());
//    }
//
//    @Test
//    void assertThatColorsListContainsExpectedValues() {
//        // given
//        // when
//        Map<Color, List<Car>> carByColor = build().getCarByColor(Color.BLACK);
//        // then
//        assertThat(carByColor)
//                .containsOnly(entry(Color.BLACK, List.of(buildBMWCar())))
//                .doesNotContain(entry(Color.WHITE, List.of(buildAUDICar())));
//
//    }
//
//    @Test
//    void shouldReturnTheMinMaxAverageMileageAndPrice() {
//        // given
//        BigDecimal minMileage = BigDecimal.valueOf(2400.0);
//        BigDecimal maxMileage = BigDecimal.valueOf(2500.0);
//        BigDecimal averageMileage = BigDecimal.valueOf(2450.0);
//        BigDecimal minPrice = BigDecimal.valueOf(1500.0);
//        BigDecimal maxPrice = BigDecimal.valueOf(1600.0);
//        BigDecimal averagePrice = BigDecimal.valueOf(1550.0);
//        // when
//        CarStatisticsPriceAndMileage carStatistics = build().getCarStatistics();
//        // then
//        assertEquals(minMileage, carStatistics.minMileage());
//        assertEquals(maxMileage, carStatistics.maxMileage());
//        assertEquals(averageMileage, carStatistics.averageMileage());
//        assertEquals(minPrice, carStatistics.minPrice());
//        assertEquals(maxPrice, carStatistics.maxPrice());
//        assertEquals(averagePrice, carStatistics.averagePrice());
//    }
//
//    @Test
//    void shouldReturnThePriceBetweenMinAndMax() {
//        // given
//        BigDecimal min = BigDecimal.valueOf(1500.0);
//        BigDecimal max = BigDecimal.valueOf(1600.0);
//        // when
//        List<Car> getPriceBetweenMinAndMax = build().filterByPrice(min, max);
//        // then
//        assertThat(getPriceBetweenMinAndMax)
//                .hasSize(2);
//
//    }

//    private Car buildBMWCar() {
//        return Car.builder()
//                .model("BMW")
//                .price(new BigDecimal("1600"))
//                .mileage(2400)
//                .color(Color.BLACK)
//                .components(List.of("DPS", "ALLOY WHEELS"))
//                .build();
//    }
//
//    private Car buildAUDICar() {
//        return Car.builder()
//                .model("AUDI")
//                .price(new BigDecimal("1500"))
//                .mileage(2500)
//                .color(Color.WHITE)
//                .components(List.of("DPS", "AIR CONDITIONING"))
//                .build();
//    }
}
