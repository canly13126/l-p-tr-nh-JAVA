
import java.util.Scanner;

public class bai6ss1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        float width, height;
        float area, circumference;

        System.out.print("Nhập chiều rộng: ");
        width = scanner.nextFloat();

        System.out.print("Nhập chiều cao: ");
        height = scanner.nextFloat();

        area = width * height;
        circumference = 2 * (width + height);

        System.out.println("Diện tích hình chữ nhật: " + area);
        System.out.println("Chu vi hình chữ nhật: " + circumference);

        scanner.close();
    }
}
