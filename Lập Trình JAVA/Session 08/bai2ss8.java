
abstract class Vehicle {
    protected String name;
    protected int speed;

    public Vehicle(String name, int speed) {
        this.name = name;
        this.speed = speed;
    }

    public abstract void displayInfo();

    public void start() {
        System.out.println("Vehicle is starting...");
    }
}

class Car extends Vehicle {
    public Car(String name, int speed) {
        super(name, speed);
    }

    @Override
    public void displayInfo() {
        System.out.println("=== THÔNG TIN XE HƠI ===");
        System.out.println("Tên xe: " + name);
        System.out.println("Tốc độ: " + speed + " km/h");
    }
}

class Bike extends Vehicle {
    public Bike(String name, int speed) {
        super(name, speed);
    }

    @Override
    public void displayInfo() {
        System.out.println("=== THÔNG TIN XE MÁY ===");
        System.out.println("Tên xe: " + name);
        System.out.println("Tốc độ: " + speed + " km/h");
    }
}

public class bai2ss8 {
    public static void main(String[] args) {
        Car car = new Car("Toyota", 180);
        Bike bike = new Bike("Honda", 100);

        car.displayInfo();
        car.start();

        System.out.println();

        bike.displayInfo();
        bike.start();
    }
}
