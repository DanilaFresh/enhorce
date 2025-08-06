package org.example.concurrency;//: concurrency/Ex5CallableDemo.java

import java.util.ArrayList;
import java.util.concurrent.*;

//Метод submit() создает объект Future, параметризованный по конкретному типу
//результата, возвращаемого Callable. К Future можно обратиться с запросом isDone(),
//чтобы проверить, не завершилась ли задача. Когда задача завершится и у нее появится
//юзультат, вы можете вызвать метод get() для получения этого результата. Метод get()
//южно вызвать и без проверки isDone(), но тогда вызов get() блокируется до готовности
//)результата. Чтобы избежать блокировки, вызовите get() с тайм-аутом или выполните
//федварительную проверку методом isDone().
class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    public String call() throws InterruptedException {
        if (id % 3 == 0)
            Thread.sleep(1000);
        return "result of TaskWithResult " + id;
    }
}

public class Ex5CallableDemo {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results =
                new ArrayList<Future<String>>();
        for (int i = 0; i < 10; i++)
            results.add(exec.submit(new TaskWithResult(i)));
        for (Future<String> fs : results)
            try {
                // get() blocks until completion:
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                System.out.println(e);
                return;
            } catch (ExecutionException e) {
                System.out.println(e);
            } finally {
                exec.shutdown();
            }
    }
} /* Output:
result of TaskWithResult 0
result of TaskWithResult 1
result of TaskWithResult 2
result of TaskWithResult 3
result of TaskWithResult 4
result of TaskWithResult 5
result of TaskWithResult 6
result of TaskWithResult 7
result of TaskWithResult 8
result of TaskWithResult 9
*///:~
