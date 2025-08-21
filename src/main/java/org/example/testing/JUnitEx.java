package org.example.testing;

enum Power{
    ZERO,ONE,TWO,THREE
}

class Calculator {
    public int add(int a, int b) {
        return a+b;
    }

    public int sub(int a, int b) {
        return a-b;
    }

    public int mul (int a, int b) {
        return a*b;
    }

    public int div(int a, int b) {
        return a/b;
    }
    public double raisToPower(Power power,int arg){
        return Math.pow(arg,power.ordinal());
    }
}

class AssertEx{
    char[] getCharArray(String str){
        return str.toCharArray();
    }
    Object getNull(){
        return null;
    }
   Integer getObject(Integer intObj){
        return intObj;
   }
   void getNPE(){
        throw new NullPointerException();
   }
}

public class JUnitEx {
}
