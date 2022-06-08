package com.holovetskyi.repo.statistics;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static com.holovetskyi.repo.statistics.StatisticsCollector.Statistics;

public class StatisticsCollector<T> implements Collector<T, Statistics<T>,
        Statistics<T>> {

    private Comparator<T> comparator;
    private BinaryOperator<T> binaryOperator;


    public StatisticsCollector(Comparator<T> comparator, BinaryOperator<T> binaryOperator) {
        this.comparator = comparator;
        this.binaryOperator = binaryOperator;
    }

    @Override
    public Supplier<Statistics<T>> supplier() {
        return Statistics::new;
    }

    @Override
    public BiConsumer<Statistics<T>, T> accumulator() {
        return (acc, element) -> {
            acc.findMinValue(element, comparator);
            acc.findMaxValue(element, comparator);
            acc.totalPrice(element, binaryOperator);
        };
    }

    @Override
    public BinaryOperator<Statistics<T>> combiner() {
        return (r1, r2) -> r1.combine(r2, comparator, binaryOperator);
        }


    @Override
    public Function<Statistics<T>, Statistics<T>> finisher() {
        return Statistics::average;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Statistics<T> {
        private static final String STATISTIC_INFO = "Min: %s, Max: %s, Average: %s.";
        private T min;
        private T max;
        private T total;
        private BigDecimal count = BigDecimal.ZERO;

        private BigDecimal average = BigDecimal.ZERO;

        public T findMinValue(T element, Comparator comparator) {
            return min = (min == null ? element : (comparator.compare(min, element) <= 0 ?
                    min : element));
        }

        public T findMaxValue(T element, Comparator<T> comparator) {
            return max = (max == null ? element : (comparator.compare(max, element) >= 0 ?
                    max : element));
        }

        public T totalPrice(T element, BinaryOperator<T> binaryOperator) {
            count = count.add(BigDecimal.ONE);
            return total = (total == null ? element : (T) binaryOperator.apply(this.total,
                    element));
        }

        public Statistics<T> average() {
            average = ((BigDecimal) total).compareTo(count) == 0 ? count :
                    ((BigDecimal) total).divide(count, 2, RoundingMode.CEILING);
            return this;
        }

        public Statistics<T> combine(Statistics<T> statisticsOther, Comparator<T> comparator, BinaryOperator<T> binaryOperator) {
            Statistics<T> statistics = new Statistics<>();

            if (Objects.isNull(min) && Objects.isNull(statisticsOther.min)) {
                statistics.min = null;
            } else if (Objects.isNull(min) && Objects.nonNull(statisticsOther.min)) {
                statistics.min = (statisticsOther.min);
            } else if (Objects.nonNull(min) && Objects.isNull(statisticsOther.min)) {
                statistics.min = (min);
            } else statistics.min = (comparator.compare(min, statisticsOther.min) <= 0 ?
                    min : statisticsOther.min);

            //max
            if (Objects.isNull(max) && Objects.isNull(statisticsOther.max)) {
                statistics.max = (null);
            } else if (Objects.isNull(max) && Objects.nonNull(statisticsOther.max)) {
                statistics.max = (statisticsOther.max);
            } else if (Objects.nonNull(max) && Objects.isNull(statisticsOther.max)) {
                statistics.max = (max);
            } else
                statistics.max = (comparator.compare(max, statisticsOther.max) <= 0 ?
                        max : statisticsOther.max);

            //average
            if (Objects.isNull(total) && Objects.isNull(statisticsOther.total)) {
                statistics.total = null;
            } else if (Objects.isNull(total) && Objects.nonNull(statisticsOther.total)) {
                statistics.total = (statisticsOther.total);
            } else if (Objects.nonNull(total) && Objects.isNull(statisticsOther.total)) {
                statistics.total = (total);
            } else {
                statistics.total = ((T) binaryOperator.apply(total, statisticsOther.total));
            }
            return statistics;
        }

        @Override
        public String toString() {
            return STATISTIC_INFO.formatted(min, max, average);
        }
    }
}
