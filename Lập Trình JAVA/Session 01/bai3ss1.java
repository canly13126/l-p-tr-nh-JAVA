
import java.util.Scanner;

public class bai3ss1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập bán kính hình tròn: ");
        double banKinh = scanner.nextDouble();

        double dienTich = Math.PI * banKinh * banKinh;

        System.out.printf("Diện tích hình tròn là: %.2f%n", dienTich);

        scanner.close();
    }
}
