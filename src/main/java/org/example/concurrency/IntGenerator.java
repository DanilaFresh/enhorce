package org.example.concurrency;//: concurrency/IntGenerator.java

//IntGenerator содержит метод cancel() для изменения состояния флага canceled,aTaK-
//же метод isCanceled() для проверки отмены. Так как флаг canceled относится к типу
//boolean, он является атомарным (то есть простые операции —такие, как присваивание
//и возвращение значения, — не прерываются при выполнении, и вы не «увидите» поле
// в промежуточном состоянии в середине этих простых операций. Ф лаг canceled также
// объявлен с ключевым словом volatile, чтобы обеспечить его видимость
public abstract class IntGenerator {
    private volatile boolean canceled = false;

    public abstract int next();

    // Allow this to be canceled:
    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }
} /// :~
