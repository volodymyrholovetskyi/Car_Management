package com.holovetskyi.car;

import com.holovetskyi.car.type.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Car {

   String model;
   BigDecimal price;
   Color color;
   BigDecimal mileage;
   List<String> components;

}

