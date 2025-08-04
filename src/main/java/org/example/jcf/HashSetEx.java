package org.example.jcf;

import java.util.HashSet;

public class HashSetEx {
    public static void main(String[] args) {


//        HashSet(): создает пустой список
//
//        HashSet(Collection<? extends E> col): создает хеш-таблицу,
//        в которую добавляет все элементы коллекции col
//
//        HashSet(int capacity): параметр capacity указывает начальную емкость таблицы,
//        которая по умолчанию равна 16
//
//        HashSet(int capacity, float koef): параметр koef или коэффициент заполнения,
//        значение которого должно быть в пределах от 0.0 до 1.0, указывает,
//        насколько должна быть заполнена емкость объектами прежде чем произойдет ее расширение.
//        Например, коэффициент 0.75 указывает,
//        что при заполнении емкости на 3/4 произойдет ее расширение.


        HashSet<String> states = new HashSet<String>();

        // добавим в список ряд элементов
        states.add("Germany");
        states.add("France");
        states.add("Italy");
        // пытаемся добавить элемент, который уже есть в коллекции
        boolean isAdded = states.add("Germany");
        System.out.println(isAdded);    // false

        System.out.printf("Set contains %d elements \n", states.size());    // 3

        for (String state : states) {

            System.out.println(state);
        }
        // удаление элемента
        states.remove("Germany");

        // хеш-таблица объектов Person
        HashSet<Person> people = new HashSet<Person>();
        people.add(new Person("Mike"));
        people.add(new Person("Tom"));
        people.add(new Person("Nick"));
        for (Person p : people) {

            System.out.println(p.getName());
        }
    }
}
