package org.example.designPatterns.creational;

//Абстрактная фабрика (Abstract factory) — порождающий шаблон проектирования,
// предоставляет интерфейс для создания семейств взаимосвязанных или взаимозависимых объектов,
// не специфицируя их конкретных классов.

// Абстрактные продукты
interface Chair {
    void sitOn();
}

interface Sofa {
    void layOn();
}

interface Table {
    void putOn();
}

// Конкретные продукты для современного стиля
class ModernChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("Сидим на современном кресле");
    }
}

class ModernSofa implements Sofa {
    @Override
    public void layOn() {
        System.out.println("Лежим на современном диване");
    }
}

class ModernTable implements Table {
    @Override
    public void putOn() {
        System.out.println("Ставим что-то на современный стол");
    }
}

// Конкретные продукты для классического стиля
class ClassicChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("Сидим на классическом кресле");
    }
}

class ClassicSofa implements Sofa {
    @Override
    public void layOn() {
        System.out.println("Лежим на классическом диване");
    }
}

class ClassicTable implements Table {
    @Override
    public void putOn() {
        System.out.println("Ставим что-то на классический стол");
    }
}

// Абстрактная фабрика
interface FurnitureFactory {
    Chair createChair();
    Sofa createSofa();
    Table createTable();
}

// Конкретная фабрика для современного стиля
class ModernFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new ModernChair();
    }

    @Override
    public Sofa createSofa() {
        return new ModernSofa();
    }

    @Override
    public Table createTable() {
        return new ModernTable();
    }
}

// Конкретная фабрика для классического стиля
class ClassicFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new ClassicChair();
    }

    @Override
    public Sofa createSofa() {
        return new ClassicSofa();
    }

    @Override
    public Table createTable() {
        return new ClassicTable();
    }
}
public class AbstractFactoryEx {
    public static void main(String[] args) {
        FurnitureFactory modernFactory = new ModernFurnitureFactory();
        Chair modernChair = modernFactory.createChair();
        Sofa modernSofa = modernFactory.createSofa();
        Table modernTable = modernFactory.createTable();

        modernChair.sitOn();
        modernSofa.layOn();
        modernTable.putOn();

        FurnitureFactory classicFactory = new ClassicFurnitureFactory();
        Chair classicChair = classicFactory.createChair();
        Sofa classicSofa = classicFactory.createSofa();
        Table classicTable = classicFactory.createTable();

        classicChair.sitOn();
        classicSofa.layOn();
        classicTable.putOn();
    }
}
