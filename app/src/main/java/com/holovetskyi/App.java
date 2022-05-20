package com.holovetskyi;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class App {

    private static final File FILE = new File("Car.json");

    public static void main(String[] args) {

        /**ADDING CARS TO JSON FILE*/
        Init.init(FILE);

        /**FROM JSON TO CARS*/
        var carsDao = new CarsImpl(FILE);


        /**SORTING*/
        System.out.println("\n-----------------SORT BY MODEL-----------------");
        String sortByModel = "model";
        List<Car> models = carsDao.sortCars(sortByModel, ASCENDING);
        models.forEach(System.out::println);

        System.out.println("\n-----------------SORT BY COLOR-----------------");
        String sortByColor = "color";
        List<Car> colors = carsDao.sortCars(sortByColor, ASCENDING);
        colors.forEach(System.out::println);

        System.out.println("\n-----------------SORT BY PRICE-----------------");
        String sortByPrice = "price";
        List<Car> price = carsDao.sortCars(sortByPrice, DESCENDING);
        price.forEach(System.out::println);

        System.out.println("\n-----------------SORT BY MILEAGE-----------------");
        String sortByMileage = "mileage";
        List<Car> mileages = carsDao.sortCars(sortByMileage, DESCENDING);
        mileages.forEach(System.out::println);

        /**MILEAGE WITH THE GREATER VALUE*/
        System.out.println("\n-----------------MILEAGE WITH THE GREATER VALUE-----------------");
        BigDecimal mileage = new BigDecimal("250");
        List<Car> carMileage = carsDao.mileageWithTheHighestValue(mileage);
        carMileage.forEach(System.out::println);

        /**GET CARS OF A SPECIFIC COLOR*/
        System.out.println("\n-----------------GET CARS OF A SPECIFIC COLOR-----------------");
        Map<Color, List<Car>> carQuantity = carsDao.getCarByColor(BLACK);
        carQuantity.forEach((k, v) -> System.out.printf(" Color: %s \n Get car by color: %s\n", k, v));


        /**EXPENSIVE CAR*/
        System.out.println("\n---------------FIND THE MOST EXPENSIVE CAR WITH A SPECIFIC MODEL NAME-------------");
        String model = "BMW";
        Map<String, List<Car>> expensiveCarByModel = carsDao.getExpensiveCarByModel(model);
        expensiveCarByModel.forEach((k, v) -> System.out.printf(" Model: %s \n Get car by model: %s\n", k, v));
//        expensiveCarByModel.forEach(car -> System.out.printf(" Model: %s \n Get car by model: %s\n", model, car));

        /**CAR STATISTICS*/
        System.out.println("\n---------------CAR STATISTICS-------------");
        CarStatisticsPriceAndMileage carStatisticsPriceAndMileage = carsDao.getCarStatistics();
        log.info(carStatisticsPriceAndMileage);

        /**THE MOST EXPENSIVE CAR*/
        System.out.println("\n---------------THE MOST EXPENSIVE CAR-------------");
        List<Car> expensiveCar = carsDao.getExpensiveCar();
        System.out.println(expensiveCar);

        /**AN ALPHABETICALLY SORTED COLLECTION OF COMPONENTS*/
        System.out.println("\n---------------AN ALPHABETICALLY SORTED COLLECTION OF COMPONENTS-------------");
        List<Car> sortedComponents = carsDao.sortedComponents();
        System.out.println(sortedComponents);
//
        /**KEY COMPONENT*/
        System.out.println("\n---------------KEY COMPONENT-------------");
        Map<List<String>, List<Car>> mapKeyComponent = carsDao.getMapKeyComponent();
        mapKeyComponent.forEach((k,v) -> System.out.printf(" Model: %s \n Get car by model: %s\n", k, v));

        /**FILTERING BY PRICE*/
        System.out.println("\n---------------FILTERING BY PRICE-------------");
        BigDecimal min = new BigDecimal("1299");
        BigDecimal max = new BigDecimal("1600");
        List<Car> filterByPrice = carsDao.filterByPrice(min, max);
        System.out.println(filterByPrice);


    }
}
