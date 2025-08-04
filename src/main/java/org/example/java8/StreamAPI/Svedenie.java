package org.example.java8.StreamAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class Svedenie {
    public static void main(String[] args) {

        ArrayList<String> names = new ArrayList<String>();
        names.addAll(Arrays.asList(new String[]{"Tom", "Sam", "Bob", "Alice"}));
        System.out.println(names.stream().count());  // 4

        // количество элементов с длиной не больше 3 символов
        System.out.println(names.stream().filter(n->n.length()<=3).count());

        ArrayList<String> names1 = new ArrayList<String>();
        names1.addAll(Arrays.asList(new String[]{"Tom", "Sam", "Bob", "Alice"}));

        Optional<String> first = names1.stream().findFirst();
        System.out.println(first.get());    // Tom

        Optional<String> any = names1.stream().findAny();
        System.out.println(first.get());    // Tom// 3

        ArrayList<String> names3 = new ArrayList<String>();
        names3.addAll(Arrays.asList(new String[]{"Tom", "Sam", "Bob", "Alice"}));

        // есть ли в потоке строка, длина которой больше 3
        boolean any1 = names3.stream().anyMatch(s->s.length()>3);
        System.out.println(any1);    // true

        // все ли строки имеют длину в 3 символа
        boolean all = names3.stream().allMatch(s->s.length()==3);
        System.out.println(all);    // false

        // НЕТ ЛИ в потоке строки "Bill". Если нет, то true, если есть, то false
        boolean none = names3.stream().noneMatch(s->s=="Bill");
        System.out.println(none);   // true)

        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.addAll(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9}));

        Optional<Integer> min = numbers.stream().min(Integer::compare);
        Optional<Integer> max = numbers.stream().max(Integer::compare);
        System.out.println(min.get());  // 1
        System.out.println(max.get());  // 9
    }
}
