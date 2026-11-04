
import java.util.Scanner;

public class bai6ss2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập số nguyên N: ");
        long n = scanner.nextLong();

        long so = Math.abs(n);
        int tong = 0;

        while (so > 0) {
            int chuSo = (int) (so % 10);
            tong += chuSo;
            so /= 10;
        }

        System.out.println("Tổng các chữ số của " + n + " là: " + tong);

        scanner.close();
    }
}
