package com.adeliosys.sample;

import java.util.HashMap;
import java.util.Map;

public class MapSample {

    /**
     * Count of occurrences of strings.
     */
    private static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        mergeSample();

        map.clear();

        computeSample();
    }

    private static void mergeSample() {
        incrementWithMerge("a"); // Prints: {a=1}
        incrementWithMerge("b"); // Prints: {a=1, b=1}
        incrementWithMerge("a"); // Prints: {a=2, b=1}
    }

    private static void incrementWithMerge(String key) {
        map.merge(key, 1, Integer::sum);

        System.out.println(map);
    }

    private static void computeSample() {
        incrementWithCompute("a"); // Prints: {a=1}
        incrementWithCompute("b"); // Prints: {a=1, b=1}
        incrementWithCompute("a"); // Prints: {a=2, b=1}
    }

    private static void incrementWithCompute(String key) {
        map.compute(key, (k, i) -> i == null ? 1 : i + 1);
        System.out.println(map);
    }
}
