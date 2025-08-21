package org.example.testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class MockExTest {
    @Mock
    List<String> mockList;
    @Spy
    List<String> mockList3;
    @Test
    public void whenMockAnnotation() {
        mockList.add("one");
        mockList.add("two");
    }
    @Test
    public void whenSpyAnnotation() {
        mockList3.add("one");
        mockList3.add("two");
    }


    @Test
    public void doReturnWhenMethodName () {
        //создаем правило: вернуть 10 при вызове метода size
        Mockito.doReturn(10).when(mockList).size();

        //тут вызывается метод и вернет 10!!
        assertEquals(10, mockList.size());
    }
    @Test
    public void whenThenReturn() {
        //создаем правило: вернуть 10 при вызове метода size
        Mockito.when(mockList.size() ).thenReturn(10);

        //тут вызывается метод и вернет 10!!
        assertEquals(10, mockList.size());
    }
    @Test
    public void whenThenThrow() {
        Mockito.when(mockList.size() ).thenThrow(IllegalStateException.class);
        assertThrows(IllegalStateException.class,()->{mockList.size();});//тут кинется исключение
    }
    @Test
    public void doReturnWhenMethNameWithParams() {
        //добавление первого правила
        Mockito.doReturn("Иван").when(mockList).get(10);
        //добавление второго правила
        Mockito.doReturn("Марья").when(mockList).get(500);

        assertEquals("Иван", mockList.get(10));
        assertEquals("Марья", mockList.get(500));

    }
    @Test
    public void doReturnAny(){
        Mockito.doReturn("Ivam").when(mockList).get(any(int.class));
        assertEquals("Ivam",mockList.get(222));
        assertEquals("Ivam",mockList.get(22222));
    }
    @Test
    public void doAnswer() {
        Mockito.doAnswer(invocation -> {
            int parameter = invocation.getArgument(0);
            return parameter * parameter;
        }).when(mockList).get(anyInt());

        assertEquals(100, mockList.get(10));
        assertEquals(25, mockList.get(5));
    }
    @Test
    public void verify() {
        //вызов метода
        String name = mockList.get(10);
        //Если закомментировать строку выше, то метод ни разу не вызовется

        //проверяем вызывался ли метод
        Mockito.verify(mockList).get(10);
    }
    @Test
    public void verifyWithTimes() {
        String name1 = mockList.get(1);  //вызов метода
        String name2 = mockList.get(2);  //вызов метода
        String name3 = mockList.get(3);  //вызов метода
//        String name4 = mockList.get(3);  //вызов метода
//        Если вызовов будет не 3, то тест провален
    //проверяем, что метод get() вызывался 3 раза
        Mockito.verify(mockList, times(3)).get(anyInt());
    }

    @Test
    public void inOrder() {
        mockList.size();
        mockList.add("a parameter");
        mockList.add("sss");
        //Работает так, что вызовы, описанные ниже могут содержать промежуточные, это на тест не влияет
        mockList.clear();

        InOrder inOrder = Mockito.inOrder(mockList);
        inOrder.verify(mockList).size();
        inOrder.verify(mockList).add("a parameter");
        inOrder.verify(mockList).clear();
    }

}
