package org.example.testing;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    static private Calculator calc;

    // Этот метод генерирует набор данных для теста
    static Stream<Arguments> provideArgumentsForAdd() {
        return Stream.of(
                Arguments.of(1, 2, 3),     // первый набор: a=1, b=2, ожидаемый результат=3
                Arguments.of(4, 5, 9),     // второй набор
                Arguments.of(10, 20, 30)   // третий набор
        );
    }

    @BeforeAll
    public static void init() {
        System.out.println("Started Tests");
        calc = new Calculator();
    }

    @BeforeEach
    public void initEach() {
        System.out.println("Started another Test method");
    }

    @Test
    public void add() {

        int result = calc.add(2, 3);
        assertEquals(5, result);
    }
    @ParameterizedTest
    @ValueSource (ints = {1,2,3,4})
    //Добавляет параметры в аргумент
    public  void addTwoSameInt(int arg){
        int result=calc.add(arg,arg);
        assertEquals(arg*2,result);
    }

    @Test
    public void sub() {

        int result = calc.sub(10, 10);
        assertEquals(0, result);
    }

    @Disabled("Отключает тест")
    @Test
    public void mul() {

        int result = calc.mul(-5, -3);
        assertEquals(15, result);
    }

    @Test
    public void div() {

        int result = calc.div(2, 3);
        assertEquals(0, result);
    }
    @Test
    @Timeout(value = 40, unit = TimeUnit.NANOSECONDS)
    public void divithTimeOut() {

        int result = calc.div(2, 3);
        assertEquals(0, result);
    }
    @ParameterizedTest
    @EnumSource(Power.class)
    //Берет параметры из класса
    void raisToPowerWithEnum(Power p) {
        double result=calc
                .raisToPower(p,2);
        double expected =Math.pow(2,p.ordinal());
        assertEquals(expected,result);
    }


    @ParameterizedTest
    @MethodSource("provideArgumentsForAdd")
        // Указываем метод, который дает Stream<Arguments>
    void testSumWithMethDource(int a, int b, int expectedSum) {
        int result=calc.add(a,b);
        assertEquals(expectedSum, result);
    }

    @ParameterizedTest
    @ArgumentsSource(ArgProviderForMult.class)
    //ууказываем класс, который через спец метод поставляет аргументы для теста
    void testMultWithArgRes(int a,int b,int expectedMult){
        int result=calc.mul(a,b);
        assertEquals(expectedMult,result);
    }
    @AfterEach
    public void endOfTest() {
        System.out.println("End of Test method");
    }

    @AfterAll
    public static void endOfTests() {
        System.out.println("End of all Tests");
    }
}