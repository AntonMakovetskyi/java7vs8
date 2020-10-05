package com.epam.cdp.m2.hw2.aggregator;

import javafx.util.Pair;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Java8ParallelAggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        return numbers.stream().parallel().reduce(0, Integer::sum);
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        return words.parallelStream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .parallelStream()
                .limit(limit)
                .map(e -> new Pair<>(e.getKey(), e.getValue()))
                .parallel()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        return words.parallelStream()
                .map(String::toUpperCase)
                .parallel()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .parallelStream()
                .filter(e -> e.getValue() > 1L)
                .map(Map.Entry::getKey)
                .sorted(new CompareByLength())
                .limit(limit)
                .parallel()
                .collect(Collectors.toList());
    }
}
