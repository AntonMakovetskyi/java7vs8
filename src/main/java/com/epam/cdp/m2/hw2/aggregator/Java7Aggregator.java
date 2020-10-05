package com.epam.cdp.m2.hw2.aggregator;

import javafx.util.Pair;

import java.util.*;

public class Java7Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        return sum;
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        Map<String, Long> wordFrequencies = new TreeMap<>();
        for (String word : words) {
            if (!wordFrequencies.containsKey(word)) {
                wordFrequencies.put(word, 0L);
            }
            Long count = wordFrequencies.get(word);
            wordFrequencies.put(word, ++count);
        }

        List<Pair<String, Long>> listOfWordFrequencies = new ArrayList<>();
        for (Map.Entry<String, Long> entry : wordFrequencies.entrySet()) {
            if (limit > 0) {
                listOfWordFrequencies.add(new Pair<>(entry.getKey(), entry.getValue()));
                limit--;
            }
        }
        return listOfWordFrequencies;
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        List<String> duplicates = findDuplicates(words);
        duplicates.sort(new CompareByLength());

        if (duplicates.size() < limit) {
            return duplicates;
        }
        return duplicates.subList(0, (int) limit);
    }


    private List<String> findDuplicates(List<String> words) {
        List<String> duplicates = new ArrayList<>();
        for (int j = 0; j < words.size(); j++) {
            for (int k = j + 1; k < words.size(); k++) {
                if (words.get(j).equalsIgnoreCase(words.get(k))
                        && !duplicates.contains(words.get(j).toUpperCase())) {
                    duplicates.add(words.get(j).toUpperCase());
                }
            }
        }
        return duplicates;
    }
}
