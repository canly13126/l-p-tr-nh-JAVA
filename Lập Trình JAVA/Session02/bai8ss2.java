
import java.util.Scanner;

public class bai8ss2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập số có 3 chữ số (100-999): ");
        int n = scanner.nextInt();

        if (n < 100 || n > 999) {
            System.out.println("Số nhập vào không hợp lệ");
        } else {
            int hundreds = n / 100;
            int tens = (n / 10) % 10;
            int units = n % 10;

            String[] so = {
                    "", "một", "hai", "ba", "bốn", "năm",
                    "sáu", "bảy", "tám", "chín"
            };

            String ketQua = so[hundreds] + " trăm";

            if (tens == 0) {
                if (units != 0) {
                    ketQua += " lẻ " + so[units];
                }
            } else if (tens == 1) {
                ketQua += " mười";

                if (units == 5) {
                    ketQua += " lăm";
                } else if (units != 0) {
                    ketQua += " " + so[units];
                }
            } else {
                ketQua += " " + so[tens] + " mươi";

                if (units == 1) {
                    ketQua += " mốt";
                } else if (units == 5) {
                    ketQua += " lăm";
                } else if (units != 0) {
                    ketQua += " " + so[units];
                }
            }

            ketQua = ketQua.substring(0, 1).toUpperCase()
                    + ketQua.substring(1);

            System.out.println(ketQua);
        }

        scanner.close();
    }
}
