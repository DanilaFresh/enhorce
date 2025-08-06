package org.example.concurrency;//: concurrency/EvenGenerator.java

// When threads collide.
//Одна задача может вызвать next() после того, как другая задача выполнила первое
// увеличение currentEvenValue, но не второе (в месте, отмеченном комментарием «Опасная
// точка»). В результате значение оказывается в «некорректном» состоянии. Чтобы
//доказать, что это возможно, EvenChecker.test() создает группу объектов EvenChecker
//для непрерывного чтения вывода EvenGenerator и проверки их всех на четность. Если
//среди значений окажется нечетное, программа сообщает об ошибке и завершается.
public class Ex15EvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;

    public int next() {
        ++currentEvenValue; // Danger point here!
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        Ex14EvenChecker.test(new Ex15EvenGenerator());
    }
} /* Output: (Sample)
Press Control-C to exit
8
*///:~
