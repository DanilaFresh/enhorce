package org.example.designPatterns.creational;
//Строитель (Builder) — порождающий шаблон проектирования,
//который предоставляет способ создания составного объекта.
//Отделяет конструирование сложного объекта от его представления так,
// что в результате одного и того же процесса конструирования могут получаться разные представления.

// 1. Продукт (Car)
class CarNew {
    private String engine;
    private String body;
    private String transmission;
    private String color;

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "engine='" + engine + '\'' +
                ", body='" + body + '\'' +
                ", transmission='" + transmission + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

// 2. Интерфейс Строителя (Builder)
interface CarBuilder {
    void setEngine(String engine);
    void setBody(String body);
    void setTransmission(String transmission);
    void setColor(String color);
    CarNew getResult();
}

// 3. Конкретный строитель (CarBuilderImpl)
class CarBuilderImpl implements CarBuilder {
    private CarNew car = new CarNew();

    @Override
    public void setEngine(String engine) {
        car.setEngine(engine);
    }

    @Override
    public void setBody(String body) {
        car.setBody(body);
    }

    @Override
    public void setTransmission(String transmission) {
        car.setTransmission(transmission);
    }

    @Override
    public void setColor(String color) {
        car.setColor(color);
    }

    @Override
    public CarNew getResult() {
        return car;
    }
}

// 4. Директор (Director)
class Director {
    private CarBuilder builder;

    public Director(CarBuilder builder) {
        this.builder = builder;
    }

    public CarNew constructSportsCar() {
        builder.setEngine("V8");
        builder.setBody("Coupe");
        builder.setTransmission("Automatic");
        builder.setColor("Red");
        return builder.getResult();
    }

    public CarNew constructFamilyCar() {
        builder.setEngine("4 cylinder");
        builder.setBody("Sedan");
        builder.setTransmission("Manual");
        builder.setColor("Blue");
        return builder.getResult();
    }
}

public class BuilderEx {
    public static void main(String[] args) {
        CarBuilder builder = new CarBuilderImpl();
        Director director = new Director(builder);

        CarNew sportsCar = director.constructSportsCar();
        System.out.println("Sports Car: " + sportsCar);

        CarNew familyCar = director.constructFamilyCar();
        System.out.println("Family Car: " + familyCar);
    }
}
