package com.holovetskyi.repo;

import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;

public record CarStatisticsPriceAndMileage(BigDecimal minPrice,
                                           BigDecimal maxPrice,
                                           BigDecimal averagePrice,
                                           BigDecimal minMileage,
                                           BigDecimal maxMileage,
                                           BigDecimal averageMileage
) {

    public static CarStatisticsPriceAndMileage toCarStatistics(DoubleSummaryStatistics statisticsPrice,
                                                               DoubleSummaryStatistics statisticsMileage) {
        return new CarStatisticsPriceAndMileage(
                BigDecimal.valueOf(statisticsPrice.getMin()),
                BigDecimal.valueOf(statisticsPrice.getMax()),
                BigDecimal.valueOf(statisticsPrice.getAverage()),
                BigDecimal.valueOf(statisticsMileage.getMin()),
                BigDecimal.valueOf(statisticsMileage.getMax()),
                BigDecimal.valueOf(statisticsMileage.getAverage())
        );
    }
}
