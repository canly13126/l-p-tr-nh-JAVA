
import java.util.Scanner;

public class bai9ss2 {
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

        System.out.println("Các số Armstrong từ 0 đến " + n + " là:");

        for (int i = 0; i <= n; i++) {
            int soChuSo = String.valueOf(i).length();
            int temp = i;
            long tong = 0;

            do {
                int chuSo = temp % 10;
                long luyThua = 1;

                for (int j = 0; j < soChuSo; j++) {
                    luyThua *= chuSo;
                }

                tong += luyThua;
                temp /= 10;
            } while (temp > 0);

            if (tong == i) {
                System.out.print(i + " ");
            }
        }

        scanner.close();
    }
}
