package org.example.designPatterns.behavioral;
//Шаблонный метод (Template Method) в Java - это поведенческий паттерн проектирования,
// который определяет скелет алгоритма в базовом классе,
// позволяя подклассам переопределять некоторые шаги алгоритма, не изменяя общую структуру

//В Java Core есть AbstractList, где есть шаблонные методы и абстрактные,
//которые необходимо реализовать конкретным реализациям

abstract class DataParser {

    // Шаблонный метод (Template Method)
    public final void parseData() {
        openSource();
        extractData();
        parseText();
        processData();
        closeSource();
    }

    // Абстрактные методы (определяются в подклассах)
    protected abstract void openSource();
    protected abstract void extractData();
    protected abstract void parseText();

    // Метод по умолчанию (может быть переопределен)
    protected void processData() {
        System.out.println("Обработка данных по умолчанию");
    }

    // Метод по умолчанию (может быть переопределен)
    protected void closeSource() {
        System.out.println("Закрытие источника данных по умолчанию");
    }
}

class CSVParser extends DataParser {
    @Override
    protected void openSource() {
        System.out.println("Открытие CSV файла");
    }

    @Override
    protected void extractData() {
        System.out.println("Извлечение данных из CSV");
    }

    @Override
    protected void parseText() {
        System.out.println("Разбор CSV текста");
    }

    @Override
    protected void processData() {
        System.out.println("Обработка CSV данных");
    }
}

class XMLParser extends DataParser {
    @Override
    protected void openSource() {
        System.out.println("Открытие XML файла");
    }

    @Override
    protected void extractData() {
        System.out.println("Извлечение данных из XML");
    }

    @Override
    protected void parseText() {
        System.out.println("Разбор XML текста");
    }

    @Override
    protected void processData() {
        System.out.println("Обработка XML данных");
    }
}
public class TemplateMethodEx {
    public static void main(String[] args) {
            DataParser csvParser = new CSVParser();
            csvParser.parseData();

            System.out.println("-----");

            DataParser xmlParser = new XMLParser();
            xmlParser.parseData();

    }
}
