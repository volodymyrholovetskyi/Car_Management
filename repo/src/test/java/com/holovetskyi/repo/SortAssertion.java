package com.holovetskyi.repo;

import com.holovetskyi.car.Car;
import com.holovetskyi.car.CarUtils;
import com.holovetskyi.repo.type.SortType;

import java.util.Comparator;
import java.util.List;

import static com.holovetskyi.car.CarUtils.*;
import static com.holovetskyi.repo.type.SortType.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class SortAssertion {

    private final List<Car> sortCars;
    SortAssertion(List<Car> sortCars) {
        this.sortCars = sortCars;
    }

    static SortAssertion than (List<Car> cars){
        return new SortAssertion(cars);
    }

   void hasBeenSortedByType(SortType sortType){
       var  audi = sortCars.get(0);
       var  bmw = sortCars.get(1);

       switch (sortType) {
           case MODEL ->  {
               Comparator<Car> compareByModel = CarUtils.compareByModel;
               assertThat(1).isEqualTo(compareByModel.compare(audi, bmw));
           }
       }
   }
}
