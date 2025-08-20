package org.example.designPatterns.structural;

//Шаблон проектирования "Адаптер" (Adapter) в Java
// позволяет двум несовместимым интерфейсам взаимодействовать между собой,
// действуя как мост. Он оборачивает несовместимый объект и предоставляет совместимый интерфейс,
// который могут использовать другие объекты.

interface Vehicle{
    String getInfo();
}

class Car implements Vehicle{
    private String carBrand;

    public Car(String carBrand) {
        this.carBrand = carBrand;
    }

    @Override
    public String getInfo() {
        return carBrand;
    }
}

class  Bike {
    private String bikeBrand;

    public String getBikeBrand() {
        return bikeBrand;
    }

    public Bike(String bikeBrand) {
        this.bikeBrand = bikeBrand;
    }
}

class BikeAdapter implements Vehicle{
    private Bike bike;

    public BikeAdapter(Bike bike) {
        this.bike = bike;
    }

    @Override
    public String getInfo() {
        return bike.getBikeBrand();
    }
}
public class AdpterEx {
    public static void main(String[] args) {
        Vehicle car=new Car("Audi");
        Bike bike=new Bike("BMX");
        Vehicle adaptedBike=new BikeAdapter(bike);

        adaptedBike.getInfo();
        adaptedBike.getInfo();

    }
}
