
import java.util.Scanner;

public class bai2ss3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int soHocVien = 0;
        double tongDiem = 0;
        double diemCaoNhat = 0;
        double diemThapNhat = 10;

        do {
            System.out.println("\n*************** MENU NHẬP ĐIỂM ***************");
            System.out.println("1. Nhập điểm học viên");
            System.out.println("2. Hiển thị thống kê");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Lựa chọn không hợp lệ!");
                scanner.nextLine();
                continue;
            }

            int luaChon = scanner.nextInt();

            switch (luaChon) {
                case 1:
                    System.out.println("\n--- NHẬP ĐIỂM HỌC VIÊN ---");

                    while (true) {
                        System.out.print("Nhập điểm (-1 để kết thúc): ");

                        if (!scanner.hasNextDouble()) {
                            System.out.println("Điểm không hợp lệ! Vui lòng nhập lại.");
                            scanner.nextLine();
                            continue;
                        }

                        double diem = scanner.nextDouble();

                        if (diem == -1) {
                            break;
                        }

                        if (diem < 0 || diem > 10) {
                            System.out.println("Điểm phải nằm trong khoảng 0-10!");
                            continue;
                        }

                        soHocVien++;
                        tongDiem += diem;

                        if (diem > diemCaoNhat) {
                            diemCaoNhat = diem;
                        }

                        if (diem < diemThapNhat) {
                            diemThapNhat = diem;
                        }

                        if (diem < 5) {
                            System.out.println("Xếp loại: Yếu");
                        } else if (diem < 7) {
                            System.out.println("Xếp loại: Trung bình");
                        } else if (diem < 8) {
                            System.out.println("Xếp loại: Khá");
                        } else if (diem < 9) {
                            System.out.println("Xếp loại: Giỏi");
                        } else {
                            System.out.println("Xếp loại: Xuất sắc");
                        }
                    }

                    System.out.println("Tổng số học viên: " + soHocVien);
                    System.out.printf("Tổng điểm: %.2f%n", tongDiem);

                    if (soHocVien > 0) {
                        System.out.printf("Điểm cao nhất: %.2f%n", diemCaoNhat);
                        System.out.printf("Điểm thấp nhất: %.2f%n", diemThapNhat);
                    }
                    break;

                case 2:
                    System.out.println("\n--- THỐNG KÊ ĐIỂM ---");

                    if (soHocVien == 0) {
                        System.out.println("Chưa có dữ liệu");
                    } else {
                        double diemTrungBinh = tongDiem / soHocVien;

                        System.out.println("Số học viên đã nhập: " + soHocVien);
                        System.out.printf("Điểm trung bình: %.2f%n", diemTrungBinh);
                        System.out.printf("Điểm cao nhất: %.2f%n", diemCaoNhat);
                        System.out.printf("Điểm thấp nhất: %.2f%n", diemThapNhat);
                    }
                    break;

                case 3:
                    System.out.println("Kết thúc chương trình!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (true);
    }
}
