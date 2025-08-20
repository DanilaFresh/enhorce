package org.example.designPatterns.creational;

//Фабричный метод (Factory Method) — порождающий шаблон проектирования,
// предоставляющий подклассам (классам-наследникам) интерфейс для создания экземпляров некоторого класса.
// В момент создания наследники могут определить, какой класс создавать.

// Интерфейс Shape
interface ShapeNew {
    void draw();
}

// Реализация Circle
class CircleNew implements ShapeNew {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}

// Реализация Rectangle
class RectangleNew implements ShapeNew {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}

// Абстрактный класс Creator (Фабрика)
abstract class ShapeFactory {
    // Фабричный метод
    abstract ShapeNew createShape(String type);

    // Метод, использующий созданный объект
    public void someOperation(String type) {
        ShapeNew shape = createShape(type);
        shape.draw();
    }
}

// Конкретный класс, реализующий фабричный метод
class ConcreteShapeFactory extends ShapeFactory {
    @Override
    ShapeNew createShape(String type) {
        if (type == null) {
            return null;
        }
        if (type.equalsIgnoreCase("CIRCLE")) {
            return new CircleNew();
        } else if (type.equalsIgnoreCase("RECTANGLE")) {
            return new RectangleNew();
        }
        return null;
    }
}
public class FactoryMethodEx {
}
