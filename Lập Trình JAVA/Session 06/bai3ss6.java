
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class bai3ss6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> danhSachBienSo = new ArrayList<>();

        int luaChon;

        do {
            System.out.println("\n******** QUẢN LÝ BIỂN SỐ XE ********");
            System.out.println("1. Thêm các biển số xe");
            System.out.println("2. Hiển thị danh sách biển số xe");
            System.out.println("3. Tìm kiếm biển số xe");
            System.out.println("4. Tìm biển số xe theo mã tỉnh");
            System.out.println("5. Sắp xếp biển số xe tăng dần");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Lựa chọn không hợp lệ!");
                scanner.nextLine();
                continue;
            }

            luaChon = scanner.nextInt();
            scanner.nextLine();

            switch (luaChon) {
                case 1:
                    System.out.print("Nhập số lượng biển số xe cần thêm: ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("Số lượng không hợp lệ!");
                        scanner.nextLine();
                        break;
                    }

                    int soLuong = scanner.nextInt();
                    scanner.nextLine();

                    if (soLuong <= 0) {
                        System.out.println("Số lượng không hợp lệ!");
                        break;
                    }

                    for (int i = 0; i < soLuong; i++) {
                        System.out.print("Nhập biển số xe thứ " + (i + 1) + ": ");
                        String bienSo = scanner.nextLine().trim().toUpperCase();

                        if (bienSo.matches("^[0-9]{2}[A-Z]-[0-9]{3}\\.[0-9]{2}$")) {
                            danhSachBienSo.add(bienSo);
                            System.out.println("Thêm thành công!");
                        } else {
                            System.out.println("Biển số không hợp lệ! Ví dụ: 30F-123.45");
                            i--;
                        }
                    }
                    break;

                case 2:
                    if (danhSachBienSo.isEmpty()) {
                        System.out.println("Danh sách biển số xe đang trống!");
                    } else {
                        System.out.println("Danh sách biển số xe:");

                        for (int i = 0; i < danhSachBienSo.size(); i++) {
                            System.out.println((i + 1) + ". " + danhSachBienSo.get(i));
                        }
                    }
                    break;

                case 3:
                    System.out.print("Nhập biển số xe cần tìm: ");
                    String bienSoCanTim = scanner.nextLine().trim().toUpperCase();

                    boolean timThay = false;

                    for (String bienSo : danhSachBienSo) {
                        if (bienSo.equals(bienSoCanTim)) {
                            timThay = true;
                            break;
                        }
                    }

                    if (timThay) {
                        System.out.println("Tìm thấy biển số xe: " + bienSoCanTim);
                    } else {
                        System.out.println("Không tìm thấy biển số xe!");
                    }
                    break;

                case 4:
                    System.out.print("Nhập mã tỉnh cần tìm: ");
                    String maTinh = scanner.nextLine().trim();

                    boolean coBienSo = false;

                    System.out.println("Các biển số xe thuộc mã tỉnh " + maTinh + ":");

                    for (String bienSo : danhSachBienSo) {
                        if (bienSo.startsWith(maTinh)) {
                            System.out.println(bienSo);
                            coBienSo = true;
                        }
                    }

                    if (!coBienSo) {
                        System.out.println("Không tìm thấy biển số xe thuộc mã tỉnh này!");
                    }
                    break;

                case 5:
                    if (danhSachBienSo.isEmpty()) {
                        System.out.println("Danh sách biển số xe đang trống!");
                        break;
                    }

                    Collections.sort(danhSachBienSo);

                    System.out.println("Danh sách biển số xe sau khi sắp xếp tăng dần:");

                    for (String bienSo : danhSachBienSo) {
                        System.out.println(bienSo);
                    }
                    break;

                case 6:
                    System.out.println("Kết thúc chương trình!");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (luaChon != 6);

        scanner.close();
    }
}
