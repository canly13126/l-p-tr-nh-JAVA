
import java.util.Scanner;

public class bai5ss1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập tử số phân số thứ nhất: ");
        int a = scanner.nextInt();

        System.out.print("Nhập mẫu số phân số thứ nhất: ");
        int b = scanner.nextInt();

        System.out.print("Nhập tử số phân số thứ hai: ");
        int c = scanner.nextInt();

        System.out.print("Nhập mẫu số phân số thứ hai: ");
        int d = scanner.nextInt();

        if (b == 0 || d == 0) {
            System.out.println("Mẫu số phải khác 0!");
        } else {
            int tuSo = a * d + b * c;
            int mauSo = b * d;

            System.out.println("Tổng hai phân số là: " + tuSo + "/" + mauSo);
        }

        scanner.close();
    }
}
