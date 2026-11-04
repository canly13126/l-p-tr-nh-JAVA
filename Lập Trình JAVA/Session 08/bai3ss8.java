
interface Colorable {
    void setColor(String color);
}

class Circle implements Colorable {
    private double radius;
    private String color;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    public void display() {
        System.out.println("=== HÌNH TRÒN ===");
        System.out.println("Bán kính: " + radius);
        System.out.println("Màu sắc: " + color);
    }
}

class Rectangle implements Colorable {
    private double length;
    private double width;
    private String color;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    public void display() {
        System.out.println("=== HÌNH CHỮ NHẬT ===");
        System.out.println("Chiều dài: " + length);
        System.out.println("Chiều rộng: " + width);
        System.out.println("Màu sắc: " + color);
    }
}

class Square implements Colorable {
    private double side;
    private String color;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    public void display() {
        System.out.println("=== HÌNH VUÔNG ===");
        System.out.println("Cạnh: " + side);
        System.out.println("Màu sắc: " + color);
    }
}

public class bai3ss8 {
    public static void main(String[] args) {
        Circle circle = new Circle(5);
        Rectangle rectangle = new Rectangle(6, 4);
        Square square = new Square(3);

        circle.setColor("Đỏ");
        rectangle.setColor("Xanh");
        square.setColor("Vàng");

        circle.display();
        System.out.println();

        rectangle.display();
        System.out.println();

        square.display();
    }
}
