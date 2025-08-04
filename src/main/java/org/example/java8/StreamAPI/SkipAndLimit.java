package org.example.java8.StreamAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SkipAndLimit {
    public static void main(String[] args) {
        List<String> phones = new ArrayList<String>();
        phones.addAll(Arrays.asList(new String[]
                {"iPhone 6 S", "Lumia 950", "Huawei Nexus 6P",
                        "Samsung Galaxy S 6", "LG G 4", "Xiaomi MI 5",
                        "ASUS Zenfone 2", "Sony Xperia Z5", "Meizu Pro 5",
                        "Lenovo S 850"}));

        int pageSize = 3; // количество элементов на страницу
        Scanner scanner = new Scanner(System.in);
        while(true){

            System.out.println("Введите номер страницы: ");
            int page = scanner.nextInt();

            if(page<1) break; // если число меньше 1, выходим из цикла

            phones.stream().skip((page-1) * pageSize)
                    .limit(pageSize)
                    .forEach(s->System.out.println(s));
        }
    }
}
