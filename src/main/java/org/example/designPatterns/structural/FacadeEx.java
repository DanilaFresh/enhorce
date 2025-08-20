package org.example.designPatterns.structural;

//Шаблон Фасад (Facade) — структурный шаблон проектирования,
// позволяющий скрыть сложность системы путем сведения всех возможных внешних вызовов к одному объекту,
// делегирующему их соответствующим объектам системы.

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//Теперь ответьте себе - какой пример паттерна фасад вы знаете в Java Core ?
//Правильно, это утилитный класс Files.
//Данный класс в себе хранит кучу полезных методов которые упрощают нам работу с файловой подсистемой.


public class FacadeEx {
    public static void main(String[] args) throws IOException {
        //Представим нам нужно вывести в консоль содержимое файла:
        try (var buffered = new BufferedReader(new FileReader(Path.of( "file.txt").toFile()))) {
            String str;
            while ((str = buffered.readLine()) != null) {
                System.out.println(str);
            }
        }
        //Или используем наш утилитный класс:
        System.out.println(
                Files.readString(Path.of("file.txt"))
        );
    }
}
