package org.example.designPatterns.behavioral;
//Паттерн "Цепочка обязанностей" (Chain of Responsibility) в Java позволяет передавать запросы
// по цепочке обработчиков,
// пока один из них не обработает запрос.

//классы фильров для servlets используют этот паттерн для
// обработки http-запросов до их поступления в  servlet

import java.util.Random;

//интерфейс обработчика
abstract class Handler {
    static Random rand = new Random();
    protected Handler nextHandler;

    abstract void handle(Req req);

    void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }
}

class Req {
}


class FirstHandler extends Handler {

    @Override
    void handle(Req req) {
        if (rand.nextInt(4) % 2==0) {
            System.out.println("FirstHandler handle");
        } else {
            nextHandler.handle(req);
        }
    }
}

class SecondHandler extends Handler {

    @Override
    void handle(Req req) {

        System.out.println("SecondHandler handle");

    }
}

public class ChainOfResponsibilityEx {
    public static void main(String[] args) {
        Handler handler1=new FirstHandler();
        Handler handler2=new SecondHandler();
        handler1.setNextHandler(handler2);

        for (int i = 0; i < 5; i++) {
            handler1.handle(new Req());
            System.out.println("Итерация "+(i+1));
        }
    }
}
