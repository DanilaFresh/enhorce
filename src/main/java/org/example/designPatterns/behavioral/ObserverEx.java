package org.example.designPatterns.behavioral;
//Наблюдатель (Observer) — поведенческий шаблон проектирования.
// Реализует механизм класса, который позволяет объекту этого класса получать
// оповещения об изменении состояния других объектов и тем самым наблюдать за ними.

import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String message);
}

// Интерфейс Наблюдаемого
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String message);
}

// Конкретный наблюдаемый объект (например, новостное агентство)
class NewsAgency implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String latestNews;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void setLatestNews(String news) {
        this.latestNews = news;
        notifyObservers("Новое сообщение: " + news);
    }
}

// Конкретный наблюдатель (например, новостной канал)
class NewsChannel implements Observer {
    private String name;

    public NewsChannel(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " получил новость: " + message);
    }
}
public class ObserverEx {
    public static void main(String[] args) {
        NewsAgency newsAgency = new NewsAgency();

        NewsChannel channel1 = new NewsChannel("Канал 1");
        NewsChannel channel2 = new NewsChannel("Канал 2");

        newsAgency.registerObserver(channel1);
        newsAgency.registerObserver(channel2);

        newsAgency.setLatestNews("Событие 1");
        // Output:
        // Канал 1 получил новость: Новое сообщение: Событие 1
        // Канал 2 получил новость: Новое сообщение: Событие 1

        newsAgency.removeObserver(channel1);

        newsAgency.setLatestNews("Событие 2");
        // Output:
        // Канал 2 получил новость: Новое сообщение: Событие 2
    }
}
