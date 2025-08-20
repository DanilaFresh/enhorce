package org.example.designPatterns.behavioral;
//Паттерн "Посетитель" (Visitor) в Java позволяет добавлять новые операции к объектам,
//не изменяя их структуру. Это достигается путем создания отдельного класса "посетителя",
//который содержит методы для обработки каждого типа объекта в иерархии. Паттерн особенно полезен,
//если нужно часто добавлять новые операции к существующей структуре классов, не модифицируя их.

//Предположим, у нас есть иерархия классов Shape (фигура), включающая Circle (круг) и Rectangle (прямоугольник).
//Мы хотим добавить возможность вычисления площади и периметра для каждой фигуры,
//не изменяя классы Circle и Rectangle.

// Абстрактный класс Shape
abstract class ShapeV {
    public abstract void accept(Visitor visitor);
}

// Конкретный класс Circle
class CircleV extends ShapeV {
    private double radius;

    public CircleV(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

// Конкретный класс Rectangle
class RectangleV extends ShapeV {
    private double width;
    private double height;

    public RectangleV(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

// Интерфейс Visitor
interface Visitor {
    void visit(CircleV circle);
    void visit(RectangleV rectangle);
}

// Конкретный Visitor для вычисления площади и периметра
class AreaCalculator implements Visitor {
    private double totalArea = 0;
    private double totalPerimeter = 0;

    @Override
    public void visit(CircleV circle) {
        totalArea += Math.PI * circle.getRadius() * circle.getRadius();
        totalPerimeter += 2 * Math.PI * circle.getRadius();
    }

    @Override
    public void visit(RectangleV rectangle) {
        totalArea += rectangle.getWidth() * rectangle.getHeight();
        totalPerimeter += 2 * (rectangle.getWidth() + rectangle.getHeight());
    }

    public double getTotalArea() {
        return totalArea;
    }

    public double getTotalPerimeter() {
        return totalPerimeter;
    }
}
public class VisitorEx {
    public static void main(String[] args) {
        ShapeV[] shapes = {new CircleV(5), new RectangleV(4, 6)};
        AreaCalculator calculator = new AreaCalculator();

        for (ShapeV shape : shapes) {
            shape.accept(calculator);
        }

        System.out.println("Total Area: " + calculator.getTotalArea());
        System.out.println("Total Perimeter: " + calculator.getTotalPerimeter());
    }
}
