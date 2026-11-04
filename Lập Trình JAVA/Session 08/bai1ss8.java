
import java.util.ArrayList;

class Animals {
    private String name;
    private int age;

    public Animals(String name, int age) {
        setName(name);
        setAge(age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên động vật không được để trống!");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Tuổi không được âm!");
        }
        this.age = age;
    }

    public void displayInfo() {
        System.out.println("Tên: " + name);
        System.out.println("Tuổi: " + age);
    }

    public String makeSound() {
        return "Some generic sound";
    }
}

class Dog extends Animals {
    private String breed;

    public Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public String makeSound() {
        return "Woof Woof";
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Giống chó: " + breed);
    }
}

class Cat extends Animals {
    private String furColor;

    public Cat(String name, int age, String furColor) {
        super(name, age);
        this.furColor = furColor;
    }

    public String getFurColor() {
        return furColor;
    }

    public void setFurColor(String furColor) {
        this.furColor = furColor;
    }

    @Override
    public String makeSound() {
        return "Meow Meow";
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Màu lông: " + furColor);
    }
}

public class bai1ss8 {
    public static void main(String[] args) {
        ArrayList<Animals> danhSach = new ArrayList<>();

        try {
            danhSach.add(new Dog("Milu", 3, "Poodle"));
            danhSach.add(new Cat("Mimi", 2, "Trắng"));
            danhSach.add(new Dog("Lucky", 5, "Husky"));
            danhSach.add(new Cat("Tom", 4, "Xám"));

            System.out.println("===== DANH SÁCH ĐỘNG VẬT =====");

            for (Animals animal : danhSach) {
                animal.displayInfo();
                System.out.println("Âm thanh: " + animal.makeSound());
                System.out.println("-----------------------------");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}
