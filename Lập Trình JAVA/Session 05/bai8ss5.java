
import java.util.Scanner;

public class bai8ss5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập chuỗi cần nén: ");
        String chuoi = scanner.nextLine();

        StringBuilder ketQua = new StringBuilder();

        if (!chuoi.isEmpty()) {
            int dem = 1;

            for (int i = 1; i < chuoi.length(); i++) {
                if (chuoi.charAt(i) == chuoi.charAt(i - 1)) {
                    dem++;
                } else {
                    ketQua.append(chuoi.charAt(i - 1));
                    ketQua.append(dem);
                    dem = 1;
                }
            }

            ketQua.append(chuoi.charAt(chuoi.length() - 1));
            ketQua.append(dem);
        }

        System.out.println("Chuỗi sau khi nén: " + ketQua);

        scanner.close();
    }
}
