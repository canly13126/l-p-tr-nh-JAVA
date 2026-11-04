
class Rectangle {
    double length;
    double width;

    public Rectangle() {
        this.length = 1;
        this.width = 1;
    }

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double getArea() {
        return length * width;
    }

    public double getPerimeter() {
        return 2 * (length + width);
    }

    public void display() {
        System.out.println("Chiều dài: " + length);
        System.out.println("Chiều rộng: " + width);
        System.out.println("Diện tích: " + getArea());
        System.out.println("Chu vi: " + getPerimeter());
    }
}

public class bai1ss7 {
    public static void main(String[] args) {
        Rectangle hcn1 = new Rectangle();
        Rectangle hcn2 = new Rectangle(5, 3);

        System.out.println("=== HÌNH CHỮ NHẬT 1 ===");
        hcn1.display();

        System.out.println("\n=== HÌNH CHỮ NHẬT 2 ===");
        hcn2.display();
    }
}
