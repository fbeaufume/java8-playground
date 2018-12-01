package com.adeliosys.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("all")
public class OptionalSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(OptionalSample.class);

    class Parent {
        List<Child> children;

        public List<Child> getChildren() {
            return children;
        }

        public Parent() {
        }

        public Parent(List<Child> children) {
            this.children = children;
        }
    }

    class Child {
        int age;

        public int getAge() {
            return age;
        }

        public Child(int age) {
            this.age = age;
        }
    }

    public int getOldestSonAge(Parent parent) {
        int age = 0;
        if (parent != null) {
            List<Child> children = parent.getChildren();
            if (children != null) {
                for (Child child : children) {
                    if (child.getAge() > age) {
                        age = child.getAge();
                    }
                }
            }
        }
        return age;
    }

    public int getOldestSonAgeWithOptional(Parent parent) {
        return Optional.ofNullable(parent) // Optional<Parent>
                .map(Parent::getChildren) // Optional<List<Child>>
                .orElseGet(ArrayList::new) // List<Child>
                .stream() // Stream<Child>
                .map(Child::getAge) // Stream<Integer>
                .max(Integer::compareTo) // Optional<Integer>
                .orElse(0); // Integer
    }

    public void processChildrenWithOptional(Parent parent) {
        Optional.ofNullable(parent)
                .map(Parent::getChildren)
                .map(List::stream)
                .ifPresent(s -> s.forEach(System.out::println));
    }

    public static void main(String[] args) {
        new OptionalSample().test();
    }

    private void test() {
        Parent parent1 = new Parent();
        Parent parent2 = new Parent(Arrays.asList(new Child(20)));
        Parent parent3 = new Parent(Arrays.asList(new Child(20), new Child(18)));

        LOGGER.info("With parent1: {}", getOldestSonAge(parent1));
        LOGGER.info("With parent1: {}", getOldestSonAgeWithOptional(parent1));
        LOGGER.info("With parent2: {}", getOldestSonAge(parent2));
        LOGGER.info("With parent2: {}", getOldestSonAgeWithOptional(parent2));
        LOGGER.info("With parent3: {}", getOldestSonAge(parent3));
        LOGGER.info("With parent3: {}", getOldestSonAgeWithOptional(parent3));
    }
}
