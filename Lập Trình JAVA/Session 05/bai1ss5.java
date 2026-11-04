
import java.util.Scanner;

public class bai1ss5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập chuỗi văn bản: ");
        String chuoi = scanner.nextLine();

        System.out.print("Nhập từ cần tìm: ");
        String tuCanTim = scanner.nextLine();

        int viTri = chuoi.indexOf(tuCanTim);

        if (viTri != -1) {
            System.out.println("Từ \"" + tuCanTim
                    + "\" xuất hiện tại vị trí " + viTri + " trong chuỗi.");
        } else {
            System.out.println("Không tìm thấy từ \"" + tuCanTim + "\" trong chuỗi.");
        }

        scanner.close();
    }
}
