
import java.util.ArrayList;
import java.util.Scanner;

public class bai10ss4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> arr = new ArrayList<>();

        int luaChon;

        do {
            System.out.println("\n========== QUẢN LÝ MẢNG SỐ NGUYÊN ==========");
            System.out.println("1. Thêm phần tử vào mảng");
            System.out.println("2. Xóa phần tử theo chỉ số");
            System.out.println("3. Hiển thị mảng");
            System.out.println("4. Sắp xếp mảng giảm dần");
            System.out.println("5. Tìm kiếm phần tử");
            System.out.println("6. Thoát");
            System.out.print("Nhập lựa chọn: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Lựa chọn không hợp lệ!");
                scanner.nextLine();
                continue;
            }

            luaChon = scanner.nextInt();

            switch (luaChon) {
                case 1:
                    System.out.print("Nhập số nguyên cần thêm: ");
                    int so = scanner.nextInt();

                    arr.add(so);
                    System.out.println("Thêm phần tử thành công!");
                    break;

                case 2:
                    if (arr.isEmpty()) {
                        System.out.println("Mảng đang rỗng!");
                        break;
                    }

                    System.out.print("Nhập chỉ số cần xóa: ");
                    int index = scanner.nextInt();

                    if (index < 0 || index >= arr.size()) {
                        System.out.println("Chỉ số không hợp lệ!");
                    } else {
                        arr.remove(index);
                        System.out.println("Xóa phần tử thành công!");
                    }
                    break;

                case 3:
                    if (arr.isEmpty()) {
                        System.out.println("Mảng đang rỗng!");
                    } else {
                        System.out.println("Các phần tử trong mảng:");

                        for (int i = 0; i < arr.size(); i++) {
                            System.out.print(arr.get(i) + " ");
                        }

                        System.out.println();
                    }
                    break;

                case 4:
                    if (arr.isEmpty()) {
                        System.out.println("Mảng đang rỗng!");
                        break;
                    }

                    for (int i = 0; i < arr.size() - 1; i++) {
                        int maxIndex = i;

                        for (int j = i + 1; j < arr.size(); j++) {
                            if (arr.get(j) > arr.get(maxIndex)) {
                                maxIndex = j;
                            }
                        }

                        int temp = arr.get(i);
                        arr.set(i, arr.get(maxIndex));
                        arr.set(maxIndex, temp);
                    }

                    System.out.println("Mảng sau khi sắp xếp giảm dần:");
                    for (int soNguyen : arr) {
                        System.out.print(soNguyen + " ");
                    }
                    System.out.println();
                    break;

                case 5:
                    if (arr.isEmpty()) {
                        System.out.println("Mảng đang rỗng!");
                        break;
                    }

                    System.out.print("Nhập số cần tìm: ");
                    int x = scanner.nextInt();

                    int viTri = -1;

                    for (int i = 0; i < arr.size(); i++) {
                        if (arr.get(i) == x) {
                            viTri = i;
                            break;
                        }
                    }

                    if (viTri != -1) {
                        System.out.println("Tìm thấy " + x
                                + " tại chỉ số " + viTri);
                    } else {
                        System.out.println("Không tìm thấy " + x + " trong mảng!");
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
