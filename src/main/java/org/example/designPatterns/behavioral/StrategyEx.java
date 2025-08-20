package org.example.designPatterns.behavioral;
//Паттерн "Стратегия" в Java позволяет динамически выбирать алгоритм выполнения в зависимости от условий.
// Он определяет семейство алгоритмов, инкапсулирует их и делает взаимозаменяемыми.
// Это позволяет изменять поведение объекта, не изменяя его структуру.

//Все просто - Comparator. Да тот самый компаратор который вряд ли кого-то обошел стороной.


//Предположим, у нас есть класс Кодировщик который может кодировать данные разными способами
// (например, с использованием шифра Цезаря, шифра Виженера, или шифра RSA).
// Вместо того, чтобы встраивать все алгоритмы в класс Кодировщик, мы можем использовать паттерн Стратегия

// 1. Интерфейс Стратегии (общий для всех алгоритмов)
interface EncodingStrategy {
    String encode(String data);
}

// 2. Конкретные стратегии (реализации интерфейса)
class CaesarCipher implements EncodingStrategy {
    private int shift;

    public CaesarCipher(int shift) {
        this.shift = shift;
    }

    @Override
    public String encode(String data) {
        StringBuilder encoded = new StringBuilder();
        for (char character : data.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                encoded.append((char) (((character - base + shift) % 26) + base));
            } else {
                encoded.append(character);
            }
        }
        return encoded.toString();
    }
}
//https://ru.wikipedia.org/wiki/%D0%A8%D0%B8%D1%84%D1%80_%D0%92%D0%B8%D0%B6%D0%B5%D0%BD%D0%B5%D1%80%D0%B0
class VigenereCipher implements EncodingStrategy {
    private String key;

    public VigenereCipher(String key) {
        this.key = key.toUpperCase();
    }

    @Override
    public String encode(String data) {
        StringBuilder encoded = new StringBuilder();
        int keyIndex = 0;
        for (char character : data.toUpperCase().toCharArray()) {
            if (Character.isLetter(character)) {
                int shift = key.charAt(keyIndex % key.length()) - 'A';
                encoded.append((char) (((character - 'A' + shift) % 26) + 'A'));
                keyIndex++;
            } else {
                encoded.append(character);
            }
        }
        return encoded.toString();
    }
}

// 3. Контекст (использует стратегию)
class Encoder {
    private EncodingStrategy strategy;

    public void setEncodingStrategy(EncodingStrategy strategy) {
        this.strategy = strategy;
    }

    public String encode(String data) {
        return strategy.encode(data);
    }
}
public class StrategyEx {
    public static void main(String[] args) {
        Encoder encoder = new Encoder();

        // Использование CaesarCipher
        encoder.setEncodingStrategy(new CaesarCipher(3));
        String encodedText1 = encoder.encode("Hello, world!");
        System.out.println("Caesar Cipher: " + encodedText1); // Вывод: Khoor, zruog!

        // Использование VigenereCipher
        encoder.setEncodingStrategy(new VigenereCipher("KEY"));
        String encodedText2 = encoder.encode("Hello, world!");
        System.out.println("Vigenere Cipher: " + encodedText2); // Вывод: LIPPS, ASVPH!
    }
}
