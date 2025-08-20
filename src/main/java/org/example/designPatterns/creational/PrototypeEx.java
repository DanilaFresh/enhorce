package org.example.designPatterns.creational;

//Прототип (Prototype)-порождающий шаблон проектирования.
//Этот паттерн задает виды создаваемых объектов с помощью экземпляра-прототипа
//и создает новые объекты путем копирования этого прототипа.

// Интерфейс прототипа
interface CloneableShape {
    CloneableShape clone();
}

// Реализация прототипа
class RectangleAnother implements CloneableShape {
    private int width;
    private int height;

    public RectangleAnother(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public CloneableShape clone() {
        return new RectangleAnother(this.width, this.height);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}

public class PrototypeEx {
    public static void main(String[] args) {
        RectangleAnother originalRectangle = new RectangleAnother(10, 5);
        System.out.println("Original Rectangle: " + originalRectangle);

        RectangleAnother clonedRectangle = (RectangleAnother) originalRectangle.clone();
        System.out.println("Cloned Rectangle: " + clonedRectangle);

        // Изменение клонированного объекта не влияет на оригинал
        clonedRectangle.setWidth(20);
        System.out.println("Cloned Rectangle after modification: " + clonedRectangle);
        System.out.println("Original Rectangle after modification of clone: " + originalRectangle);
    }
}
