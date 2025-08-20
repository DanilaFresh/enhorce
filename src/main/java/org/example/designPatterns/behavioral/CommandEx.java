package org.example.designPatterns.behavioral;
//Команда (Command) — поведенческий шаблон проектирования,
// используемый при объектно-ориентированном программировании,
// представляющий действие. Объект команды заключает в себе само действие и его параметры.
// Интерфейс Команды
interface Command {
    void execute();
    void undo(); // Добавлена возможность отмены
}

// Конкретная команда для включения устройства
class TurnOnDevice implements Command {
    private Device device;

    public TurnOnDevice(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOn();
    }

    @Override
    public void undo() {
        device.turnOff();
    }
}

// Конкретная команда для выключения устройства
class TurnOffDevice implements Command {
    private Device device;

    public TurnOffDevice(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOff();
    }

    @Override
    public void undo() {
        device.turnOn();
    }
}

// Receiver - устройство
class Device {
    private String name;

    public Device(String name) {
        this.name = name;
    }

    public void turnOn() {
        System.out.println(name + " включен");
    }

    public void turnOff() {
        System.out.println(name + " выключен");
    }
}

// Invoker - класс, который вызывает команду
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }

    public void pressUndoButton() {
        command.undo();
    }
}
public class CommandEx {
    public static void main(String[] args) {
        Device tv = new Device("Телевизор");
        Command turnOnTv = new TurnOnDevice(tv);
        Command turnOffTv = new TurnOffDevice(tv);

        RemoteControl remote = new RemoteControl();

        remote.setCommand(turnOnTv);
        remote.pressButton(); // Вывод: Телевизор включен

        remote.setCommand(turnOffTv);
        remote.pressButton(); // Вывод: Телевизор выключен

        remote.setCommand(turnOnTv);
        remote.pressButton(); // Включение
        remote.pressUndoButton(); // Отмена (выключение)

    }
}
