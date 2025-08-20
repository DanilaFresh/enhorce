package org.example.designPatterns.behavioral;
//Паттерн Mediator (Посредник) в Java предоставляет способ централизованного управления взаимодействием
//между несколькими объектами, избегая при этом жесткой связи между ними.
//Вместо того, чтобы объекты напрямую ссылались друг на друга,
//они взаимодействуют через посредника, который координирует их действия.
//Это повышает гибкость и упрощает поддержку системы,
//особенно когда количество взаимодействующих объектов велико


//Рассмотрим пример чат-комнаты, где пользователи общаются друг с другом.
// Вместо того, чтобы каждый пользователь знал о существовании и адресах других пользователей,
// они все взаимодействуют через посредника - чат-комнату

import java.util.*;

// Интерфейс Посредника
interface ChatMediator {
    void sendMessage(String msg, User user);
    void addUser(User user);
}

// Реализация Посредника (Чат-комната)
class ChatRoom implements ChatMediator {
    private List<User> users;

    public ChatRoom() {
        this.users = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public void sendMessage(String msg, User user) {
        for (User u : this.users) {
            // Сообщение отправляется всем, кроме отправителя
            if (u != user) {
                u.receive(msg);
            }
        }
    }
}

// Класс Пользователь
class User {
    private String name;
    private ChatMediator mediator;

    public User(String name, ChatMediator med) {
        this.name = name;
        this.mediator = med;
    }

    public String getName() {
        return name;
    }

    public void send(String msg) {
        System.out.println(this.name + " отправляет сообщение: " + msg);
        mediator.sendMessage(msg, this);
    }

    public void receive(String msg) {
        System.out.println(this.name + " получил сообщение: " + msg);
    }
}
public class MediatorEx {
    public static void main(String[] args) {
        ChatMediator chat = new ChatRoom();

        User john = new User("John", chat);
        User jane = new User("Jane", chat);
        User mike = new User("Mike", chat);

        chat.addUser(john);
        chat.addUser(jane);
        chat.addUser(mike);

        john.send("Привет всем!");
    }
}
