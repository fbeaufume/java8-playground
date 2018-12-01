package com.adeliosys.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

public class StreamReduceSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(StreamReduceSample.class);

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    private static void test1() {
        int[] numbers = {5, 7, 9, 11};
        int sum = Arrays.stream(numbers)
                .reduce(0, (a, b) -> a + b); // 32, "sum" method is fine too

        LOGGER.info("Sum: {}", sum);
    }

    private static void test2() {
        List<String> names = Arrays.asList("Alice", "Brian", "Charles");

        // We need this welcome message: "Hello Alice Brian Charles"

        String msg = names.stream()
                .reduce("Hello", (a, b) -> a + " " + b);

        // Yes we could have used StringJoiner instead, but it kinda ruins my example

        LOGGER.info("Msg: {}", msg);
    }

    private static void test3() {
        Map<String, String> patterns = new HashMap<>();
        patterns.put("1", "one");
        patterns.put("2", "two");
        patterns.put("3", "three");

        String input = "1 + 2 = 3";
        String output = patterns.keySet().stream()
                .reduce(input, (s, p) -> s.replace(p, patterns.get(p)));

        LOGGER.info("Output: {}", output);

        BinaryOperator<String> combinerNeverBeCalledInSequentiallyStream = (identity, t) -> {
            throw new IllegalStateException("Can't be used in parallel stream");
        };
        String output2 = patterns.entrySet().stream()
                .reduce(input, (it, var) -> it.replace(var.getKey(), var.getValue()), combinerNeverBeCalledInSequentiallyStream);

        LOGGER.info("Output2: {}", output2);
    }
}
