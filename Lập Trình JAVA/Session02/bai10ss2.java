
import java.util.Scanner;

public class bai10ss2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;

        do {
            System.out.print("Nhập số nguyên dương N: ");

            if (scanner.hasNextInt()) {
                n = scanner.nextInt();

                if (n > 0) {
                    break;
                }
            } else {
                scanner.next();
            }

            System.out.println("Số nhập vào không hợp lệ");
        } while (true);

        System.out.println("Các số Happy từ 1 đến " + n + " là:");

        for (int i = 1; i <= n; i++) {
            int so = i;

            while (so != 1 && so != 4) {
                int tong = 0;

                while (so > 0) {
                    int chuSo = so % 10;
                    tong += chuSo * chuSo;
                    so /= 10;
                }

                so = tong;
            }

            if (so == 1) {
                System.out.print(i + " ");
            }
        }

        scanner.close();
    }
}
