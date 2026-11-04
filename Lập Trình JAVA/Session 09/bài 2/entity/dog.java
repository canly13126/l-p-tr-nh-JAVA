
package entity;

public class Dog extends Pet {

    public Dog() {
        super();
    }

    public Dog(String petId, String petName, int age) {
        super(petId, petName, age);
    }

    @Override
    public void speak() {
        System.out.println("Gâu gâu");
    }

    @Override
    public void displayData() {
        System.out.println("Loại thú cưng: Chó");
        super.displayData();
    }
}
