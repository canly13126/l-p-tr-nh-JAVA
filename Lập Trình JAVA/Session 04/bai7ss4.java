
import java.util.Scanner;

public class bai7ss4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập số phần tử của mảng: ");
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("Mảng không hợp lệ");
            scanner.close();
            return;
        }

        int[] arr = new int[n];

        System.out.println("Nhập các phần tử của mảng:");
        for (int i = 0; i < n; i++) {
            System.out.print("arr[" + i + "] = ");
            arr[i] = scanner.nextInt();
        }

        int[] ketQua = new int[n];
        int soPhanTu = 0;

        for (int i = 0; i < n; i++) {
            int soLanXuatHien = 0;

            for (int j = 0; j < n; j++) {
                if (arr[i] == arr[j]) {
                    soLanXuatHien++;
                }
            }

            if (soLanXuatHien == 1) {
                ketQua[soPhanTu] = arr[i];
                soPhanTu++;
            }
        }

        if (soPhanTu == 0) {
            System.out.println("Không có phần tử nào duy nhất trong mảng");
        } else {
            System.out.println("Mảng sau khi loại bỏ các phần tử trùng lặp:");

            for (int i = 0; i < soPhanTu; i++) {
                System.out.print(ketQua[i] + " ");
            }

            System.out.println();
        }

        scanner.close();
    }
}
