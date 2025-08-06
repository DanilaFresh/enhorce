package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch=new CountDownLatch(5);
        ExecutorService executorService=Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.submit(new DoTask(latch));
            executorService.submit(new WaitTask(latch));
        }
        executorService.shutdown();
    }
}

class DoTask implements Runnable{
    private CountDownLatch latch;
    public DoTask(CountDownLatch latch){
        this.latch=latch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(5000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        latch.countDown();
    }
}

class WaitTask implements Runnable{
    public WaitTask(CountDownLatch latch) {
        this.latch = latch;
    }

    private CountDownLatch latch;
    @Override
    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Wait done");
    }
}


