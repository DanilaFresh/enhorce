package org.example.java8.StreamAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class OptinalEx {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Optional<Integer> min = numbers.stream().min(Integer::compare);
        if(min.isPresent()){

            System.out.println(min.get());
        }

        ArrayList<Integer> numbers1 = new ArrayList<Integer>();
        Optional<Integer> min1 = numbers1.stream().min(Integer::compare);
        System.out.println(min1.orElse(-1)); // -1

// непустой список
        numbers1.addAll(Arrays.asList(new Integer[]{4,5,6,7,8,9}));
        min1 = numbers1.stream().min(Integer::compare);
        System.out.println(min1.orElse(-1)); // 4

        ArrayList<Integer> numbers3 = new ArrayList<Integer>();
        Optional<Integer> min3 = numbers3.stream().min(Integer::compare);
        Random rnd = new Random();
        System.out.println(min3.orElseGet(()->rnd.nextInt(100)));

        ArrayList<Integer> numbers4 = new ArrayList<Integer>();
        Optional<Integer> min4 = numbers.stream().min(Integer::compare);
        min4.ifPresentOrElse(
                v -> System.out.println(v),
                () -> System.out.println("Value not found")
        );
    }
}
