package org.example.designPatterns.creational;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
//Объектный пул (object pool) — порождающий шаблон проектирования,
// набор инициализированных и готовых к использованию объектов.
// Когда системе требуется объект, он не создаётся,
// а берётся из пула.
// Когда объект больше не нужен, он не уничтожается, а возвращается в пул.

class ObjectPool<T> {

    private final BlockingQueue<T> pool;
    private final ObjectFactory<T> objectFactory;

    public interface ObjectFactory<T> {
        T create();
    }

    public ObjectPool(int initialSize, int maxSize, ObjectFactory<T> objectFactory) {
        this.objectFactory = objectFactory;
        this.pool = new LinkedBlockingQueue<>(maxSize);
        for (int i = 0; i < initialSize; i++) {
            pool.offer(objectFactory.create());
        }
    }

    public T checkOut() throws InterruptedException {
        T instance = pool.take();
        if (instance == null) {
            instance = objectFactory.create();
        }
        return instance;
    }

    public void checkIn(T instance) {
        if (instance != null) {
            pool.offer(instance);
        }
    }

    public int getSize() {
        return pool.size();
    }
}

public class ObjectPoolEx {
    public static void main(String[] args) throws InterruptedException {
        // Создаем пул, который будет хранить объекты типа StringBuilder
        ObjectPool<StringBuilder> stringBuilderPool = new ObjectPool<>(5, 10, StringBuilder::new);

        // Получаем объект из пула
        StringBuilder builder1 = stringBuilderPool.checkOut();
        builder1.append("Hello");
        System.out.println(builder1);

        // Возвращаем объект в пул
        stringBuilderPool.checkIn(builder1);

        // Получаем еще один объект
        StringBuilder builder2 = stringBuilderPool.checkOut();
        builder2.append(" World");
        System.out.println(builder2);

        //  Получаем доступные объекты в пуле
        System.out.println("Available objects: " + stringBuilderPool.getSize());

        //  Освобождаем ресурсы
        stringBuilderPool.checkIn(builder2);

        System.out.println("Available objects after returning: " + stringBuilderPool.getSize());
    }
}
