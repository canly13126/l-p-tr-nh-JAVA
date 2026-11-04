
package entity;

public class Cat extends Pet {

    public Cat() {
        super();
    }

    public Cat(String petId, String petName, int age) {
        super(petId, petName, age);
    }

    @Override
    public void speak() {
        System.out.println("Meo meo");
    }

    @Override
    public void displayData() {
        System.out.println("Loại thú cưng: Mèo");
        super.displayData();
    }
}
