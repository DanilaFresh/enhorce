package org.example.designPatterns.behavioral;
//Паттерн "Состояние" (State) в Java позволяет объекту изменять свое поведение
//в зависимости от своего внутреннего состояния, как будто он меняет свой класс.

//Рассмотрим пример светофора. У светофора есть состояния "Красный", "Желтый" и "Зеленый".
// В зависимости от текущего состояния, светофор должен вести себя по-разному
//(например, показывать соответствующий цвет, включать таймер и т.д.).
//Использование паттерна "Состояние" позволяет инкапсулировать логику каждого состояния в отдельный класс.

// Интерфейс состояния
interface TrafficLightState {
    void doAction(Context context);
}

// Контекст, хранит текущее состояние и делегирует ему действия
class Context {
    private TrafficLightState state;

    public Context(TrafficLightState state) {
        this.state = state;
    }

    public void setState(TrafficLightState state) {
        this.state = state;
    }

    public void doAction() {
        state.doAction(this);
    }
}

// Реализации состояний
class RedState implements TrafficLightState {
    @Override
    public void doAction(Context context) {
        System.out.println("Светофор: Красный свет.");
        context.setState(new GreenState()); // Переход на следующее состояние
    }
}

class YellowState implements TrafficLightState {
    @Override
    public void doAction(Context context) {
        System.out.println("Светофор: Желтый свет.");
        context.setState(new RedState()); // Переход на следующее состояние
    }
}

class GreenState implements TrafficLightState {
    @Override
    public void doAction(Context context) {
        System.out.println("Светофор: Зеленый свет.");
        context.setState(new YellowState()); // Переход на следующее состояние
    }
}

public class StateEx {
    public static void main(String[] args) {
        Context context = new Context(new RedState()); // Начальное состояние - красный

        context.doAction(); // Красный
        context.doAction(); // Желтый
        context.doAction(); // Зеленый
        context.doAction(); // Желтый
        context.doAction(); // Красный
    }
}
