
package presentation;

import entity.Pet;
import entity.Dog;
import entity.Cat;

import java.util.ArrayList;
import java.util.Scanner;

public class PetManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Pet> danhSach = new ArrayList<>();

        while (true) {
            System.out.println("\n******** QUẢN LÝ THÚ CƯNG ********");
            System.out.println("1. Hiển thị danh sách thú cưng");
            System.out.println("2. Thêm thú cưng");
            System.out.println("3. Gọi tiếng kêu");
            System.out.println("4. Xóa thú cưng");
            System.out.println("5. Tìm thú cưng theo tên");
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
                        System.out.println("Danh sách thú cưng đang trống!");
                    } else {
                        for (Pet pet : danhSach) {
                            pet.displayData();
                            System.out.println("---------------------------");
                        }
                    }
                    break;

                case 2:
                    System.out.print("Nhập số lượng thú cưng cần thêm: ");

                    int n;

                    try {
                        n = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Số lượng không hợp lệ!");
                        break;
                    }

                    if (n <= 0) {
                        System.out.println("Số lượng phải lớn hơn 0!");
                        break;
                    }

                    for (int i = 0; i < n; i++) {
                        System.out.println("\nThú cưng thứ " + (i + 1));

                        int loai;

                        while (true) {
                            System.out.println("1. Chó");
                            System.out.println("2. Mèo");
                            System.out.print("Chọn loại thú cưng: ");

                            try {
                                loai = Integer.parseInt(scanner.nextLine().trim());

                                if (loai == 1 || loai == 2) {
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                // Lựa chọn không hợp lệ
                            }

                            System.out.println("Vui lòng chọn 1 hoặc 2!");
                        }

                        Pet pet = loai == 1 ? new Dog() : new Cat();

                        while (true) {
                            pet.inputData(scanner);

                            boolean trungMa = false;

                            for (Pet p : danhSach) {
                                if (p.getPetId().equals(pet.getPetId())) {
                                    trungMa = true;
                                    break;
                                }
                            }

                            boolean dungLoai =
                                    (loai == 1 && pet.getPetId().startsWith("D"))
                                    || (loai == 2 && pet.getPetId().startsWith("C"));

                            if (trungMa) {
                                System.out.println("Mã thú cưng đã tồn tại! Nhập lại.");
                            } else if (!dungLoai) {
                                System.out.println("Mã chó phải bắt đầu bằng D, mã mèo phải bắt đầu bằng C!");
                            } else {
                                break;
                            }
                        }

                        danhSach.add(pet);
                        System.out.println("Thêm thú cưng thành công!");
                    }
                    break;

                case 3:
                    if (danhSach.isEmpty()) {
                        System.out.println("Danh sách thú cưng đang trống!");
                        break;
                    }

                    System.out.println("\n=== TIẾNG KÊU CỦA THÚ CƯNG ===");

                    for (Pet pet : danhSach) {
                        System.out.println("Tên: " + pet.getPetName());
                        pet.speak();
                        System.out.println("---------------------------");
                    }
                    break;

                case 4:
                    System.out.print("Nhập mã thú cưng cần xóa: ");
                    String maXoa = scanner.nextLine().trim().toUpperCase();

                    boolean daXoa = false;

                    for (int i = 0; i < danhSach.size(); i++) {
                        if (danhSach.get(i).getPetId().equals(maXoa)) {
                            danhSach.remove(i);
                            daXoa = true;
                            break;
                        }
                    }

                    if (daXoa) {
                        System.out.println("Xóa thú cưng thành công!");
                    } else {
                        System.out.println("Không tìm thấy thú cưng!");
                    }
                    break;

                case 5:
                    System.out.print("Nhập tên thú cưng cần tìm: ");
                    String tenTim = scanner.nextLine().trim().toLowerCase();

                    int dem = 0;

                    for (Pet pet : danhSach) {
                        if (pet.getPetName().toLowerCase().contains(tenTim)) {
                            pet.displayData();
                            System.out.println("---------------------------");
                            dem++;
                        }
                    }

                    if (dem == 0) {
                        System.out.println("Không tìm thấy thú cưng!");
                    } else {
                        System.out.println("Tổng số thú cưng tìm thấy: " + dem);
                    }
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
