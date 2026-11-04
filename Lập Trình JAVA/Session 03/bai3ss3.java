
import java.util.ArrayList;
import java.util.Scanner;

public class bai3ss3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> danhSachLuong = new ArrayList<>();

        int soNhanVien = 0;
        double tongLuong = 0;
        double luongCaoNhat = 0;
        double luongThapNhat = 500000000;

        do {
            System.out.println("\n*************** MENU NHẬP LƯƠNG ***************");
            System.out.println("1. Nhập lương nhân viên");
            System.out.println("2. Hiển thị thống kê");
            System.out.println("3. Tính tổng số tiền thưởng cho nhân viên");
            System.out.println("4. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Lựa chọn không hợp lệ!");
                scanner.nextLine();
                continue;
            }

            int luaChon = scanner.nextInt();

            switch (luaChon) {
                case 1:
                    System.out.println("\n--- NHẬP LƯƠNG NHÂN VIÊN ---");

                    while (true) {
                        System.out.print("Nhập lương (-1 để kết thúc): ");

                        if (!scanner.hasNextDouble()) {
                            System.out.println("Lương không hợp lệ!");
                            scanner.nextLine();
                            continue;
                        }

                        double luong = scanner.nextDouble();

                        if (luong == -1) {
                            break;
                        }

                        if (!Double.isFinite(luong) || luong < 0 || luong > 500000000) {
                            System.out.println("Lương phải từ 0 đến 500 triệu!");
                            continue;
                        }

                        danhSachLuong.add(luong);
                        soNhanVien++;
                        tongLuong += luong;

                        if (luong > luongCaoNhat) {
                            luongCaoNhat = luong;
                        }

                        if (luong < luongThapNhat) {
                            luongThapNhat = luong;
                        }

                        if (luong < 5000000) {
                            System.out.println("Thu nhập thấp");
                        } else if (luong < 15000000) {
                            System.out.println("Thu nhập trung bình");
                        } else if (luong <= 50000000) {
                            System.out.println("Thu nhập khá");
                        } else {
                            System.out.println("Thu nhập cao");
                        }
                    }

                    System.out.println("Tổng nhân viên: " + soNhanVien);
                    System.out.printf("Tổng lương: %,.0f VND%n", tongLuong);

                    if (soNhanVien > 0) {
                        System.out.printf("Lương cao nhất: %,.0f VND%n", luongCaoNhat);
                        System.out.printf("Lương thấp nhất: %,.0f VND%n", luongThapNhat);
                    }
                    break;

                case 2:
                    System.out.println("\n--- THỐNG KÊ LƯƠNG ---");

                    if (soNhanVien == 0) {
                        System.out.println("Chưa có dữ liệu");
                    } else {
                        double luongTrungBinh = tongLuong / soNhanVien;

                        System.out.println("Số nhân viên đã nhập: " + soNhanVien);
                        System.out.printf("Lương trung bình: %,.0f VND%n", luongTrungBinh);
                        System.out.printf("Lương cao nhất: %,.0f VND%n", luongCaoNhat);
                        System.out.printf("Lương thấp nhất: %,.0f VND%n", luongThapNhat);
                        System.out.printf("Tổng tiền lương: %,.0f VND%n", tongLuong);
                    }
                    break;

                case 3:
                    System.out.println("\n--- TÍNH TIỀN THƯỞNG ---");

                    if (soNhanVien == 0) {
                        System.out.println("Chưa có dữ liệu");
                        break;
                    }

                    double tongTienThuong = 0;

                    for (double luong : danhSachLuong) {
                        double tyLeThuong;

                        if (luong < 5000000) {
                            tyLeThuong = 0.05;
                        } else if (luong < 15000000) {
                            tyLeThuong = 0.10;
                        } else if (luong < 50000000) {
                            tyLeThuong = 0.15;
                        } else if (luong <= 100000000) {
                            tyLeThuong = 0.20;
                        } else {
                            tyLeThuong = 0.25;
                        }

                        tongTienThuong += luong * tyLeThuong;
                    }

                    System.out.printf("Tổng tiền thưởng: %,.0f VND%n", tongTienThuong);
                    break;

                case 4:
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
