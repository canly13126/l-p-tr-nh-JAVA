
package entity;

import java.util.Scanner;

public abstract class Pet {
    private String petId;
    private String petName;
    private int age;

    public Pet() {
    }

    public Pet(String petId, String petName, int age) {
        this.petId = petId;
        this.petName = petName;
        this.age = age;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void inputData(Scanner scanner) {
        while (true) {
            System.out.print("Nhập mã thú cưng (C/D và 3 chữ số): ");
            String id = scanner.nextLine().trim().toUpperCase();

            if (id.matches("[CD][0-9]{3}")) {
                petId = id;
                break;
            }

            System.out.println("Mã thú cưng không hợp lệ!");
        }

        while (true) {
            System.out.print("Nhập tên thú cưng (20-50 ký tự): ");
            String name = scanner.nextLine().trim();

            if (name.length() >= 20 && name.length() <= 50) {
                petName = name;
                break;
            }

            System.out.println("Tên phải có từ 20 đến 50 ký tự!");
        }

        while (true) {
            System.out.print("Nhập tuổi thú cưng: ");

            try {
                int inputAge = Integer.parseInt(scanner.nextLine().trim());

                if (inputAge > 0) {
                    age = inputAge;
                    break;
                }
            } catch (NumberFormatException e) {
                // Dữ liệu không phải số nguyên
            }

            System.out.println("Tuổi phải là số nguyên lớn hơn 0!");
        }
    }

    public void displayData() {
        System.out.println("Mã thú cưng: " + petId);
        System.out.println("Tên thú cưng: " + petName);
        System.out.println("Tuổi: " + age);
    }

    public abstract void speak();
}
