# Car Collection Management

---
## Application for managing cars in ___multi-modular___ structure
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
---

## ***General info***

#### The project is divided into modules:
- repo
    - (I) CarRepo
    - (C) CarRepoImpl
    - (R) CarStatisticsPriceAndMileage
    - (E) Direction
- domain
    - type
        - (E) Color
    - (C) Car
    - (I) CarUtils
    - (C) CarValidation
- exception
    - (C) CarExceptionReported
- json
    - (C) CarConverter
    - (C) Converter
- validation
    - (I) Validation
- init
    - (C) Init



The project creates a list of cars based on the ***Car*** object in the _domain module_, each ***Car*** class field <br>
is validated, then the created object goes to the `Car.json` file, then the data is downloaded from the files <br>
and *deserialize* to the object and saved to the car list, in the dao module in addition to the list, we also <br>
have a set of methods that provide us with the following functionalities: <br>
```
1. Car sorting by model name, color, price and mileage;
2. Return a car with a mileage greater than the given value;
3. Return the map, whose key is a color and the value is the number of cars that have that color;
4. The method prints the statistics (min, max, average for price and mileage);
5. The method returns the car with the highest price;
6. The method returns a car with sorted components;
7. The method returns a list of cars with a price range (from, to);
8. Return the map, whose key is component;

```
---
## ***Technologies***

- Docker
- Git
- JSON
- JUnit, AssertJ
- Java 18
- Lombok
- Multi-module
- Stream API

---

## ***Setup***

### Use the following command to clone project:

```git clone @github.com:volodymyrholovetskyi/car_collection_management.git```

---







