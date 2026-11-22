import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkedList<User> userList = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n*************** MENU QUẢN LÝ NGƯỜI DÙNG ***************");
            System.out.println("1. Thêm người dùng");
            System.out.println("2. Xóa người dùng theo email");
            System.out.println("3. Hiển thị danh sách người dùng");
            System.out.println("4. Thoát");
            System.out.print("Chọn chức năng (1-4): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Vui lòng chỉ nhập số từ 1 đến 4!");
                continue;
            }

            switch (choice) {
                case 1:
                    addUser(scanner, userList);
                    break;
                case 2:
                    deleteUserByEmail(scanner, userList);
                    break;
                case 3:
                    displayUsers(userList);
                    break;
                case 4:
                    System.out.println("Đã thoát chương trình.");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        }
    }

    private static void addUser(Scanner scanner, LinkedList<User> userList) {
        String name;
        while (true) {
            try {
                System.out.print("Nhập tên người dùng: ");
                name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("Tên người dùng không được để trống!");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        }

        String email;
        while (true) {
            try {
                System.out.print("Nhập email: ");
                email = scanner.nextLine().trim();
                if (email.isEmpty() || !email.contains("@")) {
                    throw new IllegalArgumentException("Email không hợp lệ hoặc bị bỏ trống!");
                }
                boolean isDuplicate = false;
                for (User user : userList) {
                    if (user.getEmail().equalsIgnoreCase(email)) {
                        isDuplicate = true;
                        break;
                    }
                }
                if (isDuplicate) {
                    throw new IllegalArgumentException("Email này đã tồn tại trong danh sách!");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        }

        String phone;
        while (true) {
            try {
                System.out.print("Nhập số điện thoại: ");
                phone = scanner.nextLine().trim();
                if (phone.isEmpty() || !phone.matches("\\d{9,11}")) {
                    throw new IllegalArgumentException("Số điện thoại phải gồm 9-11 chữ số!");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        }

        userList.add(new User(name, email, phone));
        System.out.println("✅ Thêm người dùng thành công!");
    }

    private static void deleteUserByEmail(Scanner scanner, LinkedList<User> userList) {
        if (userList.isEmpty()) {
            System.out.println("⚠️ Danh sách người dùng hiện đang trống.");
            return;
        }

        System.out.print("Nhập email người dùng cần xóa: ");
        String emailToDelete = scanner.nextLine().trim();

        Iterator<User> iterator = userList.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getEmail().equalsIgnoreCase(emailToDelete)) {
                iterator.remove();
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("✅ Đã xóa người dùng có email \"" + emailToDelete + "\" thành công!");
        } else {
            System.out.println("❌ Không tìm thấy người dùng nào có email \"" + emailToDelete + "\".");
        }
    }

    private static void displayUsers(LinkedList<User> userList) {
        if (userList.isEmpty()) {
            System.out.println("⚠️ Danh sách người dùng hiện đang trống.");
            return;
        }

        System.out.println("\n--- DANH SÁCH NGƯỜI DÙNG ---");
        int index = 1;
        for (User user : userList) {
            System.out.println(index + ". " + user);
            index++;
        }
    }
}
