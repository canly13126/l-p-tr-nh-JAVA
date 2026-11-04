
import java.util.Scanner;

public class bai7ss2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập cạnh a: ");
        int a = scanner.nextInt();

        System.out.print("Nhập cạnh b: ");
        int b = scanner.nextInt();

        System.out.print("Nhập cạnh c: ");
        int c = scanner.nextInt();

        if (a <= 0 || b <= 0 || c <= 0 ||
                (long) a + b <= c ||
                (long) a + c <= b ||
                (long) b + c <= a) {
            System.out.println("Ba cạnh không tạo thành tam giác.");
        } else if (a == b && b == c) {
            System.out.println("Tam giác đều");
        } else if (a == b || a == c || b == c) {
            System.out.println("Tam giác cân");
        } else if ((long) a * a + (long) b * b == (long) c * c ||
                   (long) a * a + (long) c * c == (long) b * b ||
                   (long) b * b + (long) c * c == (long) a * a) {
            System.out.println("Tam giác vuông");
        } else {
            System.out.println("Tam giác thường");
        }

        scanner.close();
    }
}
