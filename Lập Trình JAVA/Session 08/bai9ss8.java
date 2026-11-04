
import java.util.ArrayList;

abstract class Car {
    protected String model;
    protected int year;
    protected double price;

    public Car(String model, int year, double price) {
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public String getInfo() {
        return model + " (Năm SX: " + year + ", Giá: $" + price + ")";
    }

    public abstract String start();

    public abstract String stop();
}

interface Refuelable {
    void refuel();
}

class ElectricCar extends Car implements Refuelable {
    public ElectricCar(String model, int year, double price) {
        super(model, year, price);
    }

    @Override
    public String start() {
        return "Xe điện " + getInfo() + " đã khởi động không tiếng ồn.";
    }

    @Override
    public String stop() {
        return "Xe điện " + getInfo() + " đã dừng và ngắt kết nối động cơ.";
    }

    @Override
    public void refuel() {
        System.out.println("Xe điện " + getInfo() + " đang sạc điện...");
    }
}

class GasCar extends Car implements Refuelable {
    public GasCar(String model, int year, double price) {
        super(model, year, price);
    }

    @Override
    public String start() {
        return "Xe chạy xăng " + getInfo() + " đã khởi động với tiếng động cơ.";
    }

    @Override
    public String stop() {
        return "Xe chạy xăng " + getInfo() + " đã dừng và động cơ tắt.";
    }

    @Override
    public void refuel() {
        System.out.println("Xe chạy xăng " + getInfo() + " đang được đổ xăng...");
    }
}

class Garage {
    private ArrayList<Car> cars;

    public Garage() {
        cars = new ArrayList<>();
    }

    public void addCar(Car car) {
        if (car != null) {
            cars.add(car);
            System.out.println("Thêm xe vào gara thành công!");
        }
    }

    public void startAll() {
        System.out.println("\n===== KHỞI ĐỘNG TẤT CẢ XE =====");

        for (Car car : cars) {
            System.out.println(car.start());
        }
    }

    public void stopAll() {
        System.out.println("\n===== DỪNG TẤT CẢ XE =====");

        for (Car car : cars) {
            System.out.println(car.stop());
        }
    }

    public void refuelAll() {
        System.out.println("\n===== NẠP NHIÊN LIỆU =====");

        for (Car car : cars) {
            if (car instanceof Refuelable) {
                Refuelable refuelable = (Refuelable) car;
                refuelable.refuel();
            }
        }
    }
}

public class bai9ss8 {
    public static void main(String[] args) {
        Garage garage = new Garage();

        ElectricCar electricCar1 = new ElectricCar(
                "Tesla Model 3", 2024, 35000
        );

        ElectricCar electricCar2 = new ElectricCar(
                "VinFast VF8", 2023, 40000
        );

        GasCar gasCar1 = new GasCar(
                "Toyota Camry", 2022, 30000
        );

        GasCar gasCar2 = new GasCar(
                "Honda Civic", 2021, 25000
        );

        garage.addCar(electricCar1);
        garage.addCar(electricCar2);
        garage.addCar(gasCar1);
        garage.addCar(gasCar2);

        garage.startAll();
        garage.refuelAll();
        garage.stopAll();
    }
}
