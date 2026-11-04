
import java.util.Scanner;

public class bai1ss6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double[] diem = new double[100];
        int n = 0;
        int luaChon;

        do {
            System.out.println("\n******** QUẢN LÝ ĐIỂM SV ********");
            System.out.println("1. Nhập danh sách điểm sinh viên");
            System.out.println("2. In danh sách điểm");
            System.out.println("3. Tính điểm trung bình của các sinh viên");
            System.out.println("4. Tìm điểm cao nhất và thấp nhất");
            System.out.println("5. Đếm số lượng sinh viên đạt và trượt");
            System.out.println("6. Sắp xếp điểm tăng dần");
            System.out.println("7. Thống kê số lượng sinh viên giỏi và xuất sắc");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Lựa chọn không hợp lệ!");
                scanner.next();
                continue;
            }

            luaChon = scanner.nextInt();

            switch (luaChon) {
                case 1:
                    System.out.print("Nhập số lượng sinh viên: ");
                    int soLuong = scanner.nextInt();

                    if (soLuong <= 0 || soLuong > diem.length) {
                        System.out.println("Số lượng không hợp lệ!");
                        break;
                    }

                    n = soLuong;

                    for (int i = 0; i < n; i++) {
                        do {
                            System.out.print("Nhập điểm sinh viên " + (i + 1) + ": ");

                            if (!scanner.hasNextDouble()) {
                                System.out.println("Điểm không hợp lệ!");
                                scanner.next();
                                continue;
                            }

                            diem[i] = scanner.nextDouble();

                            if (diem[i] < 0 || diem[i] > 10) {
                                System.out.println("Điểm phải từ 0 đến 10!");
                            } else {
                                break;
                            }
                        } while (true);
                    }

                    System.out.println("Nhập danh sách thành công!");
                    break;

                case 2:
                    if (n == 0) {
                        System.out.println("Chưa có dữ liệu!");
                        break;
                    }

                    System.out.println("Danh sách điểm sinh viên:");
                    for (int i = 0; i < n; i++) {
                        System.out.println("Sinh viên " + (i + 1) + ": " + diem[i]);
                    }
                    break;

                case 3:
                    if (n == 0) {
                        System.out.println("Chưa có dữ liệu!");
                        break;
                    }

                    double tong = 0;

                    for (int i = 0; i < n; i++) {
                        tong += diem[i];
                    }

                    System.out.printf("Điểm trung bình: %.2f%n", tong / n);
                    break;

                case 4:
                    if (n == 0) {
                        System.out.println("Chưa có dữ liệu!");
                        break;
                    }

                    double max = diem[0];
                    double min = diem[0];

                    for (int i = 1; i < n; i++) {
                        if (diem[i] > max) {
                            max = diem[i];
                        }

                        if (diem[i] < min) {
                            min = diem[i];
                        }
                    }

                    System.out.println("Điểm cao nhất: " + max);
                    System.out.println("Điểm thấp nhất: " + min);
                    break;

                case 5:
                    if (n == 0) {
                        System.out.println("Chưa có dữ liệu!");
                        break;
                    }

                    int dat = 0;
                    int truot = 0;

                    for (int i = 0; i < n; i++) {
                        if (diem[i] >= 5) {
                            dat++;
                        } else {
                            truot++;
                        }
                    }

                    System.out.println("Số sinh viên đạt: " + dat);
                    System.out.println("Số sinh viên trượt: " + truot);
                    break;

                case 6:
                    if (n == 0) {
                        System.out.println("Chưa có dữ liệu!");
                        break;
                    }

                    for (int i = 0; i < n - 1; i++) {
                        for (int j = 0; j < n - 1 - i; j++) {
                            if (diem[j] > diem[j + 1]) {
                                double temp = diem[j];
                                diem[j] = diem[j + 1];
                                diem[j + 1] = temp;
                            }
                        }
                    }

                    System.out.println("Danh sách điểm tăng dần:");
                    for (int i = 0; i < n; i++) {
                        System.out.print(diem[i] + " ");
                    }
                    System.out.println();
                    break;

                case 7:
                    if (n == 0) {
                        System.out.println("Chưa có dữ liệu!");
                        break;
                    }

                    int gioi = 0;
                    int xuatSac = 0;

                    for (int i = 0; i < n; i++) {
                        if (diem[i] >= 8) {
                            gioi++;

                            if (diem[i] >= 9) {
                                xuatSac++;
                            }
                        }
                    }

                    System.out.println("Số sinh viên giỏi và xuất sắc: " + gioi);
                    System.out.println("Trong đó số sinh viên xuất sắc: " + xuatSac);
                    break;

                case 8:
                    System.out.println("Kết thúc chương trình!");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (luaChon != 8);

        scanner.close();
    }
}
