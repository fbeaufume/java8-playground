package com.adeliosys.sample;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@SuppressWarnings("all")
public class StreamFlatMapSample {

    // May be overridden and return null
    List<String> getDefaultValues() {
        return Arrays.asList("foo", "bar");
    }

    // May be overridden
    List<String> getCustomValues() {
        return null;
    }

    private void test() {
        // Process default and custom values
        Stream.of(getDefaultValues(), getCustomValues()) // Stream<List<String>>
                .filter(Objects::nonNull) // Stream<List<String>>
                .flatMap(List::stream) // Stream<string>
                .forEach(s -> useTheValue(s));
    }

    private void useTheValue(String s) {
    }

    public static void main(String[] args) {
        new StreamFlatMapSample().test();
    }
}
