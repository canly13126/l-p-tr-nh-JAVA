import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Contact> contactSet = new LinkedHashSet<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== QUẢN LÝ DANH BẠ ĐIỆN THOẠI =====");
            System.out.println("1. Thêm liên lạc");
            System.out.println("2. Xóa liên lạc theo số điện thoại");
            System.out.println("3. Tìm kiếm liên lạc theo số điện thoại");
            System.out.println("4. Hiển thị danh sách liên lạc (Phân trang)");
            System.out.println("5. Thoát");
            System.out.print("Chọn chức năng (1-5): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Vui lòng chỉ nhập số từ 1 đến 5!");
                continue;
            }

            switch (choice) {
                case 1:
                    addContact(scanner, contactSet);
                    break;
                case 2:
                    deleteContact(scanner, contactSet);
                    break;
                case 3:
                    searchContact(scanner, contactSet);
                    break;
                case 4:
                    displayContactsWithPagination(scanner, contactSet);
                    break;
                case 5:
                    System.out.println("Đã thoát chương trình.");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        }
    }

    private static void addContact(Scanner scanner, Set<Contact> contactSet) {
        String name;
        while (true) {
            System.out.print("Nhập tên người liên lạc: ");
            name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                break;
            }
            System.out.println("❌ Lỗi: Tên người liên lạc không được để trống!");
        }

        String phoneNumber;
        while (true) {
            System.out.print("Nhập số điện thoại: ");
            phoneNumber = scanner.nextLine().trim();
            if (phoneNumber.matches("\\d{9,11}")) {
                break;
            }
            System.out.println("❌ Lỗi: Số điện thoại phải gồm 9-11 chữ số!");
        }

        if (contactSet.contains(Contact.forSearch(phoneNumber))) {
            System.out.println("❌ Thông báo: Số điện thoại \"" + phoneNumber + "\" đã tồn tại trong danh bạ!");
            return;
        }

        Contact newContact = new Contact(name, phoneNumber);
        if (contactSet.add(newContact)) {
            System.out.println("✅ Thêm liên lạc thành công! (Tổng số liên lạc: " + contactSet.size() + ")");
        }
    }

    private static void deleteContact(Scanner scanner, Set<Contact> contactSet) {
        if (contactSet.isEmpty()) {
            System.out.println("⚠️ Danh bạ hiện đang trống.");
            return;
        }

        System.out.print("Nhập số điện thoại cần xóa: ");
        String phoneNumber = scanner.nextLine().trim();

        if (contactSet.remove(Contact.forSearch(phoneNumber))) {
            System.out.println("✅ Đã xóa liên lạc có số \"" + phoneNumber + "\" thành công! (Còn lại: " + contactSet.size() + ")");
        } else {
            System.out.println("❌ Không tìm thấy liên lạc nào có số điện thoại \"" + phoneNumber + "\".");
        }
    }

    private static void searchContact(Scanner scanner, Set<Contact> contactSet) {
        if (contactSet.isEmpty()) {
            System.out.println("⚠️ Danh bạ hiện đang trống.");
            return;
        }

        System.out.print("Nhập số điện thoại cần tìm: ");
        String phoneNumber = scanner.nextLine().trim();

        if (contactSet.contains(Contact.forSearch(phoneNumber))) {
            for (Contact contact : contactSet) {
                if (contact.getPhoneNumber().equals(phoneNumber)) {
                    System.out.println("✅ Tìm thấy liên lạc:");
                    System.out.println(contact);
                    return;
                }
            }
        } else {
            System.out.println("❌ Không tìm thấy liên lạc nào có số điện thoại \"" + phoneNumber + "\".");
        }
    }

    private static void displayContactsWithPagination(Scanner scanner, Set<Contact> contactSet) {
        if (contactSet.isEmpty()) {
            System.out.println("⚠️ Danh bạ hiện đang trống.");
            return;
        }

        List<Contact> contactList = new ArrayList<>(contactSet);
        int pageSize = 3;
        int totalItems = contactList.size();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);
        int currentPage = 1;

        while (true) {
            int startIndex = (currentPage - 1) * pageSize;
            int endIndex = Math.min(startIndex + pageSize, totalItems);

            System.out.println("\n--- DANH BẠ ĐIỆN THOẠI (Trang " + currentPage + "/" + totalPages + " | Tổng: " + totalItems + " liên lạc) ---");
            for (int i = startIndex; i < endIndex; i++) {
                System.out.println(contactList.get(i));
            }

            if (totalPages == 1) {
                break;
            }

            System.out.println("[N] Trang sau | [P] Trang trước | [Số trang] Nhảy tới trang | [0] Quay lại menu");
            System.out.print("Lựa chọn của bạn: ");
            String input = scanner.nextLine().trim();

            if (input.equals("0")) {
                break;
            } else if (input.equalsIgnoreCase("N")) {
                if (currentPage < totalPages) {
                    currentPage++;
                } else {
                    System.out.println("⚠️ Bạn đang ở trang cuối cùng!");
                }
            } else if (input.equalsIgnoreCase("P")) {
                if (currentPage > 1) {
                    currentPage--;
                } else {
                    System.out.println("⚠️ Bạn đang ở trang đầu tiên!");
                }
            } else {
                try {
                    int targetPage = Integer.parseInt(input);
                    if (targetPage >= 1 && targetPage <= totalPages) {
                        currentPage = targetPage;
                    } else {
                        System.out.println("❌ Lỗi: Số trang phải từ 1 đến " + totalPages + "!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("❌ Lỗi: Lựa chọn không hợp lệ!");
                }
            }
        }
    }
}
