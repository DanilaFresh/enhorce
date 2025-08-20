package org.example.designPatterns.structural;

//Шаблон Мост (Bridge) — структурный шаблон проектирования,
// используемый чтобы “разделять абстракцию и реализацию так,
// чтобы они могли изменяться независимо”.

//abstract class Shape {
//    public abstract void draw();
//}

//если бы был конструкторо без color, то пришлось бы налсдеовать новые классы с учетом всех вохможных цветов
// но есть поле Color которое и выступает в качестве моста(bridge)
abstract class Shape {
    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    public abstract void draw();
}
class Rectangle extends Shape {
    public Rectangle(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("Drawing rectangle");
    }
}
class Triangle extends Shape {
    public Triangle(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("Drawing triangle");
    }
}

interface Color {
    void fillColor();
}

class BlackColor implements Color {
    @Override
    public void fillColor() {
        System.out.println("Filling in black color");
    }
}

class GreenColor implements Color {
    @Override
    public void fillColor() {
        System.out.println("Filling in green color");
    }
}

public class BridgeEx {

}
