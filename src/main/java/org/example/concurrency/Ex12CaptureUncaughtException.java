package org.example.concurrency;//: concurrency/CaptureUncaughtException.java

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
//Чтобы решить проблему, мы изменим способ создания потоков объектом Executor.
//B Java SE5 появился новый интерфейс Thread.UncaughtExceptionHandler, который
//позволяет связать с каждым объектом Thread обработчик исключения. Метод Thread.
//        UncaughtExceptionHandler.uncaughtException() вызывается автоматически тогда, когда
//поток собирается прекратить свое существование из-за неперехваченного исключения.
//Чтобы использовать этот интерфейс, мы создадим новый тип ThreadFactory, который
//присоединяет новый объект Thread.UncaughtExceptionHandler к каждому создаваемо
//му объекту Thread. Созданный класс-фабрика передается методу Executors, который
//создает новый экземпляр ExecutorService:
class ExceptionThread2 implements Runnable {
  public void run() {
    Thread t = Thread.currentThread();
    System.out.println("run() by " + t);
    System.out.println(
      "eh = " + t.getUncaughtExceptionHandler());
    throw new RuntimeException();
  }
}

class MyUncaughtExceptionHandler implements
Thread.UncaughtExceptionHandler {
  public void uncaughtException(Thread t, Throwable e) {
    System.out.println("caught " + e);
  }
}

class HandlerThreadFactory implements ThreadFactory {
  public Thread newThread(Runnable r) {
    System.out.println(this + " creating new Thread");
    Thread t = new Thread(r);
    System.out.println("created " + t);
    t.setUncaughtExceptionHandler(
      new MyUncaughtExceptionHandler());
    System.out.println(
      "eh = " + t.getUncaughtExceptionHandler());
    return t;
  }
}

public class Ex12CaptureUncaughtException {
  public static void main(String[] args) {
    ExecutorService exec = Executors.newCachedThreadPool(
      new HandlerThreadFactory());
    exec.execute(new ExceptionThread2());
    exec.shutdown();
  }
} /* Output: (90% match)
HandlerThreadFactory@de6ced creating new Thread
created Thread[Thread-0,5,main]
eh = MyUncaughtExceptionHandler@1fb8ee3
run() by Thread[Thread-0,5,main]
eh = MyUncaughtExceptionHandler@1fb8ee3
caught java.lang.RuntimeException
*///:~
