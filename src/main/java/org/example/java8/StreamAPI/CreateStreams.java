package org.example.java8.StreamAPI;

import java.util.*;
import java.util.stream.*;

public class CreateStreams {
    public static void main(String[] args) {

        ArrayList<String> cities = new ArrayList<String>();
        Collections.addAll(cities, "Париж", "Лондон", "Мадрид");
        cities.stream() // получаем поток
                .filter(s -> s.length() == 6) // применяем фильтрацию по длине строки
                .forEach(s -> System.out.println(s)); // выводим отфильтрованные строки на консоль

        IntStream intStream = Arrays.stream(new int[]{1,2,4,5,7});
        intStream.forEach(i->System.out.println(i));

        LongStream longStream = Arrays.stream(new long[]{100,250,400,5843787,237});
        longStream.forEach(l->System.out.println(l));

        DoubleStream doubleStream = Arrays.stream(new double[] {3.4, 6.7, 9.5, 8.2345, 121});
        doubleStream.forEach(d->System.out.println(d));

        // можно передать массив
        String[] cities1 = {"Париж", "Лондон", "Мадрид"};
        Stream<String> citiesStream2 =Stream.of(cities1);

        IntStream intStream1 = IntStream.of(1,2,4,5,7);
        intStream1.forEach(i->System.out.println(i));

        LongStream longStream1 = LongStream.of(100,250,400,5843787,237);
        longStream1.forEach(l->System.out.println(l));

        DoubleStream doubleStream1 = DoubleStream.of(3.4, 6.7, 9.5, 8.2345, 121);
        doubleStream1.forEach(d->System.out.println(d));
    }
}
