package org.example.jcf;

import java.util.*;

public class QueueDequeuArrayDeque {
    public static void print(Object obj) {
        System.out.println(obj);
    }

    public static void main(String[] args) {
        //Обобщенный интерфейс Queue<E> расширяет базовый интерфейс Collection
        // и определяет поведение класса в качестве однонаправленной очереди.
        // Свою функциональность он раскрывает через следующие методы:
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < 5; i++) {
            queue.add(i);// добавляем
        }
        print(queue);

        print("i=queue.element()");
        Integer i = queue.element();
        //Возвращает, но не удаляет, элемент из начала очереди.
        // Если очередь пуста, генерирует исключение NoSuchElementException
        print(queue);

        print("i=queue.offer(5)");
        queue.offer(5);
        //Добавляет элемент  в конец очереди.
        // Если элемент удачно добавлен, возвращает true, иначе - false
        print(queue);

        print("i=queue.peek();");
        i = queue.peek();
        //Возвращает без удаления элемент из начала очереди.
        // Если очередь пуста, возвращает значение null
        print(i);
        print(queue);

        print("i=queue.poll();");
        i = queue.poll();
//      возвращает с удалением элемент из начала очереди.
//       Если очередь пуста, возвращает значение null
        print(i);
        print(queue);

        print("i=queue.remove();");
        i = queue.remove();
//        возвращает с удалением элемент из начала очереди.
//        Если очередь пуста, генерирует исключение NoSuchElementException
        print(i);
        print(queue);

        print("_________\nDeque\n");

        Deque<Integer> deque = new LinkedList<>();

        for (int j = 0; j < 5; j++) {
            deque.add(i * 10);
        }

        print(deque);

        print("deque.addFirst(11);");
        deque.addFirst(11);
        print(deque);
        //добавляет элемент в начало очереди
        print("deque.addLast(99);");
        deque.addLast(99);
        // добавляет элемент obj в конец очереди
        print(deque);

        print("deque.getFirst();");
        print(deque.getFirst());
        print(deque);
        //Возвращает без удаления элемент из головы очереди.
        // Если очередь пуста, генерирует исключение NoSuchElementException
        print("deque.getLast();");
        print(deque.getLast());
        // Возвращает без удаления последний элемент очереди.
        // Если очередь пуста, генерирует исключение NoSuchElementException
        print(deque);

        print("deque.offerFirst(12);");
        print(deque.offerFirst(12));
        print(deque);
        //Добавляет элемент obj в самое начало очереди.
        // Если элемент удачно добавлен, возвращает true, иначе - false
        print("deque.offerLast(98);");
        print(deque.offerLast(98));
        // Добавляет элемент obj в конец очереди.
        // Если элемент удачно добавлен, возвращает true, иначе - false
        print(deque);

        print("deque.peekFirst()");
        print(deque.peekFirst());
        print(deque);
        //возвращает без удаления элемент из начала очереди.
        // Если очередь пуста, возвращает значение null
        print("deque.peekLast()");
        print(deque.peekLast());
        //Возвращает без удаления элемент из начала очереди.
        // Если очередь пуста, возвращает значение null
        print("deque.pollFirst()");
        print(deque.pollFirst());
        print(deque);
        //Возвращает с удалением элемент из начала очереди.
        // Если очередь пуста, возвращает значение null
        print("deque.pollLast()");
        print(deque.pollLast());
        print(deque);
        //Возвращает с удалением элемент из конца очереди.
        // Если очередь пуста, возвращает значение null

        print("deque.pop()");
        print(deque.pop());
        print(deque);
        //Возвращает с удалением элемент из начала очереди.
        // Если очередь пуста, генерирует исключение NoSuchElementException
        print("deque.push(111)");
        deque.push(111);
        print(deque);
        //Возвращает с удалением элемент из начала очереди.
        // Если очередь пуста, генерирует исключение NoSuchElementException

        print("_________\nArrayDeque\n");

        ArrayDeque<String> states = new ArrayDeque<String>();
        // стандартное добавление элементов
        states.add("Germany");
        states.addFirst("France"); // добавляем элемент в самое начало
        states.push("Great Britain"); // добавляем элемент в самое начало
        states.addLast("Spain"); // добавляем элемент в конец коллекции
        states.add("Italy");

        // получаем первый элемент без удаления
        String sFirst = states.getFirst();
        System.out.println(sFirst);     // Great Britain
        // получаем последний элемент без удаления
        String sLast = states.getLast();
        System.out.println(sLast);      // Italy

        System.out.printf("Queue size: %d \n", states.size());  // 5

        // перебор коллекции
        while (states.peek() != null) {
            // извлечение c начала
            System.out.println(states.pop());
        }

        // очередь из объектов Person
        ArrayDeque<Person> people = new ArrayDeque<Person>();
        people.addFirst(new Person("Tom"));
        people.addLast(new Person("Nick"));
        // перебор без извлечения
        for (Person p : people) {

            System.out.println(p.getName());
        }
    }
}

class Person {

    private String name;

    public Person(String value) {

        name = value;
    }

    String getName() {
        return name;
    }
}
