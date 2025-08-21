package org.example.testing;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.MethodName.class)
class AssertExTest {
    private static AssertEx obj;
    @BeforeAll
    static void setObj(){
        obj=new AssertEx();
    }
    @Test
    @DisplayName("Arrays by equals()")
    public void getCharArrayTest(){
        String str="Test";
        char[] result=obj.getCharArray(str);
        assertArrayEquals(str.toCharArray(), result);
    }
    @Test
    public void getCharArrayTestForNotNull(){
        String str="Test";
        char[] result=obj.getCharArray(str);
        assertNotNull(result);
    }

    @Test
    public void getNullTest(){
        Object ref=obj.getNull();
        assertNull(ref,"It is not  Null");
    }
    @Test
    public void getObjSameTest(){
        Integer intObj=new Integer(1);

        assertSame( obj.getObject(intObj),intObj);
    }
    @Test
    public void getObjNotSameTest(){
        Integer intObj=new Integer(1);
        assertAll(
                ()->{assertFalse(false);},
                ()->{assertNotSame( new Integer(1),obj.getObject(intObj));}
        );

    }
    @Test
    public void getNPE(){
        assertThrows(NullPointerException.class, ()->{
            obj.getNPE();
        });
    }

}