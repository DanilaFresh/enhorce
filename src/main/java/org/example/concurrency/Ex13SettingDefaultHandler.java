package org.example.concurrency;//: concurrency/SettingDefaultHandler.java

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//Предыдущий пример позволяет назначать обработчики для каждого конкретного
//случая. Если вы знаете, что один обработчик события будет использоваться везде, еще
//проще установить обработчик неперехваченных исключений по умолчанию, который
//задается в статическом поле класса Thread:
public class Ex13SettingDefaultHandler {
  public static void main(String[] args) {
    Thread.setDefaultUncaughtExceptionHandler(
      new MyUncaughtExceptionHandler());
    ExecutorService exec = Executors.newCachedThreadPool();
    exec.execute(new Ex11ExceptionThread());
//    exec.shutdown();
  }
} /* Output:
caught java.lang.RuntimeException
*///:~
