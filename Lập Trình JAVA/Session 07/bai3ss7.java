
import java.util.Scanner;

class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

public class bai3ss7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Person person1 = new Person();
        Person person2 = new Person();

        System.out.println("=== NHẬP THÔNG TIN NGƯỜI THỨ NHẤT ===");

        System.out.print("Nhập tên: ");
        person1.setName(scanner.nextLine());

        System.out.print("Nhập tuổi: ");
        person1.setAge(scanner.nextInt());
        scanner.nextLine();

        System.out.println("\n=== NHẬP THÔNG TIN NGƯỜI THỨ HAI ===");

        System.out.print("Nhập tên: ");
        person2.setName(scanner.nextLine());

        System.out.print("Nhập tuổi: ");
        person2.setAge(scanner.nextInt());

        System.out.println("\n=== KẾT QUẢ SO SÁNH ===");

        if (person1.getAge() > person2.getAge()) {
            System.out.println(person1.getName() + " lớn tuổi hơn "
                    + person2.getName());
        } else if (person1.getAge() < person2.getAge()) {
            System.out.println(person2.getName() + " lớn tuổi hơn "
                    + person1.getName());
        } else {
            System.out.println(person1.getName() + " và "
                    + person2.getName() + " bằng tuổi nhau");
        }

        scanner.close();
    }
}
