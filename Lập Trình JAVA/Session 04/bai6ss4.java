
import java.util.Scanner;

public class bai6ss4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập kích thước mảng: ");
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("Kích thước mảng không hợp lệ!");
            scanner.close();
            return;
        }

        int[] arr = new int[n];

        System.out.println("Nhập các phần tử của mảng:");
        for (int i = 0; i < n; i++) {
            System.out.print("arr[" + i + "] = ");
            arr[i] = scanner.nextInt();
        }

        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[maxIndex];
            arr[maxIndex] = temp;
        }

        System.out.println("\nMảng sau khi sắp xếp giảm dần:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.print("\n\nNhập số cần tìm: ");
        int x = scanner.nextInt();

        int viTriTuyenTinh = -1;

        for (int i = 0; i < n; i++) {
            if (arr[i] == x) {
                viTriTuyenTinh = i;
                break;
            }
        }

        int left = 0;
        int right = n - 1;
        int viTriNhiPhan = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == x) {
                viTriNhiPhan = mid;
                break;
            } else if (arr[mid] < x) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if (viTriTuyenTinh != -1) {
            System.out.println("Tìm kiếm tuyến tính: Tìm thấy " + x
                    + " tại vị trí " + viTriTuyenTinh);
        } else {
            System.out.println("Tìm kiếm tuyến tính: Không tìm thấy " + x);
        }

        if (viTriNhiPhan != -1) {
            System.out.println("Tìm kiếm nhị phân: Tìm thấy " + x
                    + " tại vị trí " + viTriNhiPhan);
        } else {
            System.out.println("Tìm kiếm nhị phân: Không tìm thấy " + x);
        }

        scanner.close();
    }
}
