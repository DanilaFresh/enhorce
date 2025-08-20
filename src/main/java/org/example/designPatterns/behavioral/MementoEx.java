package org.example.designPatterns.behavioral;
//Паттерн «Хранитель» (Memento) в Java позволяет сохранять и восстанавливать состояние объекта,
// не нарушая инкапсуляции. Это поведенческий паттерн,
// который часто используется для реализации функциональности отката изменений
// или сохранения состояния для последующего восстановления.

import java.util.*;

//Класс, который хранит состояние
class Memento {
    private final String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
//Класс, чье состояние сохраняется
class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Memento save() {
        return new Memento(state);
    }

    public void restore(Memento memento) {
        state = memento.getState();
    }
}
//Хранит объекты Mememnto
class Caretaker {
    private final List<Memento> history = new ArrayList<>();
    private final Originator originator;

    public Caretaker(Originator originator) {
        this.originator = originator;
    }

    public void backup() {
        history.add(originator.save());
    }

    public void undo() {
        if (history.isEmpty()) {
            return;
        }
        Memento memento = history.remove(history.size() - 1);
        originator.restore(memento);
    }
}

public class MementoEx {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker(originator);

        originator.setState("State #1");
        caretaker.backup();
        originator.setState("State #2");
        caretaker.backup();
        originator.setState("State #3");

        System.out.println("Current state: " + originator.getState()); // Вывод: Current state: State #3

        caretaker.undo();
        System.out.println("Restored to previous state: " + originator.getState()); // Вывод: Restored to previous state: State #2

        caretaker.undo();
        System.out.println("Restored to previous state: " + originator.getState()); // Вывод: Restored to previous state: State #1
    }
}
