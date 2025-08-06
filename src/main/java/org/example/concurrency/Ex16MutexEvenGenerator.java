package org.example.concurrency;//: concurrency/MutexEvenGenerator.java
// Preventing thread collisions with mutexes.
// {RunByHand}

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//В библиотеку SE5 java.util.concurrent также включен явный механизм мьютексов,
//определенных в java.util.concurrent.locks. Операции создания, установления и снятия бло
//кировки с объектом Lock выполняются явно, и код получается менее элегантным, чем
//во встроенной форме. С другой стороны, такой подход обладает большей гибкостью
//при решении некоторых видов задач. Вот как выглядит пример SynchronizedEvenGenera-
//tor.java, переписанный для явного использования объектов Lock:
public class Ex16MutexEvenGenerator extends IntGenerator {
  private int currentEvenValue = 0;
  private Lock lock = new ReentrantLock();
  public int next() {
    lock.lock();
    try {
      ++currentEvenValue;
      Thread.yield(); // Cause failure faster
      ++currentEvenValue;
      return currentEvenValue;
    } finally {
      lock.unlock();
    }
  }
  public static void main(String[] args) {
    Ex14EvenChecker.test(new Ex16MutexEvenGenerator());
  }
} ///:~
