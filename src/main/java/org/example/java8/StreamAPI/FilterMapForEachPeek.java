package org.example.java8.StreamAPI;

import java.util.stream.Stream;

public class FilterMapForEachPeek {
    public static void main(String[] args) {
        Stream<String> citiesStream = Stream.of("Париж", "Лондон", "Мадрид", "Берлин", "Брюссель");
        citiesStream.forEach(s -> System.out.println(s));

        Stream<String> citiesStream1 = Stream.of("Париж", "Лондон", "Мадрид", "Берлин", "Брюссель");
        citiesStream1.filter(s -> s.length() == 6).forEach(s -> System.out.println(s));

        Stream<Phone> phoneStream = Stream.of(new Phone("iPhone 6 S", 54000), new Phone("Lumia 950", 45000),
                new Phone("Samsung Galaxy S 6", 40000));

        phoneStream.filter(p -> p.getPrice() < 50000).forEach(p -> System.out.println(p.getName()));

        Stream<Phone> phoneStream1 = Stream.of(new Phone("iPhone 6 S", 54000), new Phone("Lumia 950", 45000),
                new Phone("Samsung Galaxy S 6", 40000));

        phoneStream1
                .map(p -> p.getName()) // помещаем в поток только названия телефонов
                .forEach(s -> System.out.println(s));

        Stream<Phone> phoneStream3 = Stream.of(new Phone("iPhone 6 S", 54000), new Phone("Lumia 950", 45000),
                new Phone("Samsung Galaxy S 6", 40000));

        phoneStream3
                .flatMap(p -> Stream.of(
                        String.format("название: %s  цена без скидки: %d", p.getName(), p.getPrice()),
                        String.format("название: %s  цена со скидкой: %d", p.getName(), p.getPrice() - (int) (p.getPrice() * 0.1))
                ))
                .forEach(s -> System.out.println(s));

        Stream<Phone> phoneStream4 = Stream.of(new Phone("iPhone 6 S", 54000), new Phone("Lumia 950", 45000),
                new Phone("Samsung Galaxy S 6", 40000));

        phoneStream4
                .peek(phone -> phone.setPrice(phone.getPrice()+1000))
                .forEach(phone -> System.out.println(phone.getName()+" "+phone.getPrice()));
    }



}

class Phone {

    private String name;
    private int price;

    public Phone(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
