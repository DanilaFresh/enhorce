package org.example.designPatterns.structural;
//Заместитель (Proxy) — структурный шаблон проектирования, предоставляющий объект,
// который контролирует доступ к другому объекту,
// перехватывая и пропуская через себя все его вызовы.

//тут прокси логирует кол-во вызовов

interface DataSrc {
    String getData();
}

class StandartDataSrc implements DataSrc {
    private String data = "Data";

    public StandartDataSrc() {

    }

    @Override
    public String getData() {
        return data;
    }
}


class StandartDataSrcProxy implements DataSrc {
    private StandartDataSrc standartDataSrc;
    private long countGetDataInvokes=0;
    public StandartDataSrcProxy(StandartDataSrc src){
        this.standartDataSrc=src;
    }


    @Override
    public String getData() {
        countGetDataInvokes++;
        return standartDataSrc.getData();
    }
}

class DataConsumer{
    public void consumeData(DataSrc src){

    }
}

public class ProxyEx {
    public static void main(String[] args) {
        StandartDataSrcProxy srcProxy=new StandartDataSrcProxy(new StandartDataSrc());
        DataConsumer dataConsumer=new DataConsumer();
        dataConsumer.consumeData(srcProxy);
    }
}
