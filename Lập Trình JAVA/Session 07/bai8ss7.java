
import java.util.ArrayList;
import java.util.Scanner;

enum Gender {
    MALE, FEMALE, OTHER
}

class StudentSS7 {
    private int id;
    private String name;
    private int age;
    private Gender gender;
    private String address;
    private String phoneNumber;

    public StudentSS7() {
    }

    public StudentSS7(int id, String name, int age, Gender gender,
                      String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void inputData(Scanner scanner) {
        System.out.print("Nhập mã sinh viên: ");
        id = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhập tên sinh viên: ");
        name = scanner.nextLine();

        System.out.print("Nhập tuổi: ");
        age = Integer.parseInt(scanner.nextLine());

        while (true) {
            System.out.print("Nhập giới tính (MALE/FEMALE/OTHER): ");

            try {
                gender = Gender.valueOf(scanner.nextLine().trim().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Giới tính không hợp lệ!");
            }
        }

        System.out.print("Nhập địa chỉ: ");
        address = scanner.nextLine();

        System.out.print("Nhập số điện thoại: ");
        phoneNumber = scanner.nextLine();
    }

    public void displayData() {
        System.out.println("Mã sinh viên: " + id);
        System.out.println("Tên sinh viên: " + name);
        System.out.println("Tuổi: " + age);
        System.out.println("Giới tính: " + gender);
        System.out.println("Địa chỉ: " + address);
        System.out.println("Số điện thoại: " + phoneNumber);
        System.out.println("------------------------------");
    }
}

public class bai8ss7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<StudentSS7> danhSach = new ArrayList<>();

        while (true) {
            System.out.println("\n========== QUẢN LÝ SINH VIÊN ==========");
            System.out.println("1. Hiển thị danh sách tất cả sinh viên");
            System.out.println("2. Thêm mới sinh viên");
            System.out.println("3. Sửa thông tin sinh viên theo mã");
            System.out.println("4. Xóa sinh viên");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            int luaChon;

            try {
                luaChon = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ!");
                continue;
            }

            switch (luaChon) {
                case 1:
                    if (danhSach.isEmpty()) {
                        System.out.println("Danh sách sinh viên đang trống!");
                    } else {
                        for (StudentSS7 sv : danhSach) {
                            sv.displayData();
                        }
                    }
                    break;

                case 2:
                    try {
                        StudentSS7 sinhVienMoi = new StudentSS7();
                        sinhVienMoi.inputData(scanner);

                        boolean trungMa = false;

                        for (StudentSS7 sv : danhSach) {
                            if (sv.getId() == sinhVienMoi.getId()) {
                                trungMa = true;
                                break;
                            }
                        }

                        if (trungMa) {
                            System.out.println("Mã sinh viên đã tồn tại!");
                        } else {
                            danhSach.add(sinhVienMoi);
                            System.out.println("Thêm sinh viên thành công!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Mã sinh viên hoặc tuổi không hợp lệ!");
                    }
                    break;

                case 3:
                    System.out.print("Nhập mã sinh viên cần sửa: ");

                    try {
                        int maSua = Integer.parseInt(scanner.nextLine());
                        StudentSS7 sinhVienSua = null;

                        for (StudentSS7 sv : danhSach) {
                            if (sv.getId() == maSua) {
                                sinhVienSua = sv;
                                break;
                            }
                        }

                        if (sinhVienSua == null) {
                            System.out.println("Không tìm thấy sinh viên!");
                            break;
                        }

                        System.out.print("Nhập tên mới: ");
                        sinhVienSua.setName(scanner.nextLine());

                        System.out.print("Nhập tuổi mới: ");
                        sinhVienSua.setAge(Integer.parseInt(scanner.nextLine()));

                        while (true) {
                            System.out.print("Nhập giới tính mới (MALE/FEMALE/OTHER): ");

                            try {
                                Gender gioiTinh = Gender.valueOf(
                                        scanner.nextLine().trim().toUpperCase()
                                );
                                sinhVienSua.setGender(gioiTinh);
                                break;
                            } catch (IllegalArgumentException e) {
                                System.out.println("Giới tính không hợp lệ!");
                            }
                        }

                        System.out.print("Nhập địa chỉ mới: ");
                        sinhVienSua.setAddress(scanner.nextLine());

                        System.out.print("Nhập số điện thoại mới: ");
                        sinhVienSua.setPhoneNumber(scanner.nextLine());

                        System.out.println("Cập nhật sinh viên thành công!");
                    } catch (NumberFormatException e) {
                        System.out.println("Dữ liệu nhập không hợp lệ!");
                    }
                    break;

                case 4:
                    System.out.print("Nhập mã sinh viên cần xóa: ");

                    try {
                        int maXoa = Integer.parseInt(scanner.nextLine());
                        boolean daXoa = false;

                        for (int i = 0; i < danhSach.size(); i++) {
                            if (danhSach.get(i).getId() == maXoa) {
                                danhSach.remove(i);
                                daXoa = true;
                                break;
                            }
                        }

                        if (daXoa) {
                            System.out.println("Xóa sinh viên thành công!");
                        } else {
                            System.out.println("Không tìm thấy sinh viên!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Mã sinh viên không hợp lệ!");
                    }
                    break;

                case 5:
                    System.out.println("Kết thúc chương trình!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
