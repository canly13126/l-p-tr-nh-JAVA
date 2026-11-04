
package presentation;

import entity.Student;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> danhSach = new ArrayList<>();

        while (true) {
            System.out.println("\n******** QUẢN LÝ SINH VIÊN ********");
            System.out.println("1. Hiển thị danh sách sinh viên");
            System.out.println("2. Thêm sinh viên");
            System.out.println("3. Cập nhật thông tin sinh viên theo mã");
            System.out.println("4. Xóa sinh viên theo mã");
            System.out.println("5. Tìm sinh viên theo tên");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            int luaChon;

            try {
                luaChon = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ!");
                continue;
            }

            switch (luaChon) {
                case 1:
                    if (danhSach.isEmpty()) {
                        System.out.println("Danh sách sinh viên đang trống!");
                    } else {
                        for (Student sv : danhSach) {
                            sv.displayData();
                        }
                    }
                    break;

                case 2:
                    System.out.print("Nhập số lượng sinh viên cần thêm: ");

                    try {
                        int n = Integer.parseInt(scanner.nextLine().trim());

                        if (n <= 0) {
                            System.out.println("Số lượng không hợp lệ!");
                            break;
                        }

                        for (int i = 0; i < n; i++) {
                            System.out.println("\nNhập sinh viên thứ " + (i + 1));

                            Student sv = new Student();
                            sv.inputData(scanner);

                            boolean trungMa = false;

                            for (Student s : danhSach) {
                                if (s.getStudentId().equals(sv.getStudentId())) {
                                    trungMa = true;
                                    break;
                                }
                            }

                            if (trungMa) {
                                System.out.println("Mã sinh viên đã tồn tại!");
                            } else {
                                danhSach.add(sv);
                                System.out.println("Thêm sinh viên thành công!");
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Dữ liệu nhập không hợp lệ!");
                    }
                    break;

                case 3:
                    System.out.print("Nhập mã sinh viên cần cập nhật: ");
                    String maSua = scanner.nextLine();

                    Student svSua = null;

                    for (Student sv : danhSach) {
                        if (sv.getStudentId().equals(maSua)) {
                            svSua = sv;
                            break;
                        }
                    }

                    if (svSua == null) {
                        System.out.println("Mã sinh viên không tồn tại");
                        break;
                    }

                    try {
                        System.out.print("Nhập tên mới: ");
                        String tenMoi = scanner.nextLine();

                        System.out.print("Nhập tuổi mới: ");
                        int tuoiMoi = Integer.parseInt(scanner.nextLine().trim());

                        System.out.print("Nhập chuyên ngành mới: ");
                        String nganhMoi = scanner.nextLine();

                        svSua.setStudentName(tenMoi);
                        svSua.setAge(tuoiMoi);
                        svSua.setMajor(nganhMoi);

                        System.out.println("Cập nhật thành công!");
                    } catch (NumberFormatException e) {
                        System.out.println("Tuổi không hợp lệ!");
                    }
                    break;

                case 4:
                    System.out.print("Nhập mã sinh viên cần xóa: ");
                    String maXoa = scanner.nextLine();

                    boolean daXoa = false;

                    for (int i = 0; i < danhSach.size(); i++) {
                        if (danhSach.get(i).getStudentId().equals(maXoa)) {
                            danhSach.remove(i);
                            daXoa = true;
                            break;
                        }
                    }

                    if (daXoa) {
                        System.out.println("Xóa sinh viên thành công!");
                    } else {
                        System.out.println("Mã sinh viên không tồn tại");
                    }
                    break;

                case 5:
                    System.out.print("Nhập tên sinh viên cần tìm: ");
                    String tenTim = scanner.nextLine().trim().toLowerCase();

                    int dem = 0;

                    for (Student sv : danhSach) {
                        if (sv.getStudentName().toLowerCase().contains(tenTim)) {
                            sv.displayData();
                            dem++;
                        }
                    }

                    System.out.println("Tổng số sinh viên tìm thấy: " + dem);
                    break;

                case 6:
                    System.out.println("Kết thúc chương trình!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
