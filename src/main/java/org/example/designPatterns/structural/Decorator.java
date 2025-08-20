package org.example.designPatterns.structural;
//Декоратор (Decorator) — структурный шаблон проектирования,
// предназначенный для динамического подключения дополнительного поведения к объекту.
// Шаблон Декоратор предоставляет хорошую и гибкую альтернативу практике создания подклассов с
// целью расширения функциональности.

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

//FilterReader это декоратор, а SpellingInputStream это его реализация, в которой мы убираем все буквы 'ё' на 'e'
class SpellingInputStream extends FilterReader {
    protected SpellingInputStream(Reader in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int c = super.read();

        return c == 'ё' ? 'е' : c == 'Ё' ? 'Е' : c;
    }
}

// Тут обратный декоратор для примера
class SpellingReverseInputStream extends FilterReader {
    protected SpellingReverseInputStream(Reader in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int c = super.read();

        return c == 'е' ? 'ё' : c == 'Е' ? 'Ё' : c;
    }
}
//Тут мы динамически подключаем к объекту допфункционал
public class Decorator {
    public static void main(String[] args) throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Random random = new Random();
        if (random.nextInt(2) == 0) {
            try (var buffered = new SpellingInputStream(
                    new BufferedReader(Files.newBufferedReader(Path.of( "file.txt"))))) {
                int b;
                while ((b = buffered.read()) != -1) {
                    System.out.print((char) b);
                }
            }
        } else {
            try (var buffered = new SpellingReverseInputStream(
                    new BufferedReader(Files.newBufferedReader(Path.of( "file.txt"))))) {
                int b;
                while ((b = buffered.read()) != -1) {
                    System.out.print((char) b);
                }
            }
        }
    }
}
