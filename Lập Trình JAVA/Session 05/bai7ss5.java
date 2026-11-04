
import java.util.Random;
import java.util.Scanner;

public class bai7ss5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Nhập độ dài chuỗi n (1-1000): ");
        int n = scanner.nextInt();

        if (n < 1 || n > 1000) {
            System.out.println("Độ dài không hợp lệ!");
            scanner.close();
            return;
        }

        String kyTu = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvwxyz"
                + "0123456789";

        StringBuilder maGia = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int viTri = random.nextInt(kyTu.length());
            maGia.append(kyTu.charAt(viTri));
        }

        System.out.println("Mã giả ngẫu nhiên: " + maGia);

        scanner.close();
    }
}
