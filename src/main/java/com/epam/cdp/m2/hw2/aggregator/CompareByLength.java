package com.epam.cdp.m2.hw2.aggregator;

import java.util.Comparator;

public class CompareByLength implements Comparator<String> {

    @Override
    public int compare(String word, String anotherWord) {
        int len1 = word.length();
        int len2 = anotherWord.length();
        int lim = Math.min(len1, len2);
        char[] v1 = word.toCharArray();
        char[] v2 = anotherWord.toCharArray();

        if (len1 == len2) {
            int k = 0;
            while (k < lim) {
                char c1 = v1[k];
                char c2 = v2[k];
                if (c1 != c2) {
                    return c1 - c2;
                }
                k++;
            }
        }
        return len1 - len2;
    }
}
