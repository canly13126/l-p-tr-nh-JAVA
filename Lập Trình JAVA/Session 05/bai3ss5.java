

import java.util.Scanner;

public class bai3ss5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập chuỗi: ");
        String chuoi = scanner.nextLine();

        String ketQua = chuoi.replaceAll("[0-9]", "*");

        System.out.println("Chuỗi sau khi thay thế: " + ketQua);

        scanner.close();
    }
}
