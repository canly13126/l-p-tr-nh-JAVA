import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InvoiceManager manager = new InvoiceManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n*************** MENU QUẢN LÝ HÓA ĐƠN ***************");
            System.out.println("1. Thêm hóa đơn");
            System.out.println("2. Sửa hóa đơn");
            System.out.println("3. Xóa hóa đơn");
            System.out.println("4. Hiển thị danh sách hóa đơn");
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
                    String invoiceId;
                    while (true) {
                        try {
                            System.out.print("Nhập mã hóa đơn: ");
                            invoiceId = scanner.nextLine().trim();
                            if (invoiceId.isEmpty()) {
                                throw new IllegalArgumentException("Mã hóa đơn không được để trống!");
                            }
                            if (manager.isIdExists(invoiceId)) {
                                throw new IllegalArgumentException("Mã hóa đơn này đã tồn tại!");
                            }
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("❌ Lỗi: " + e.getMessage());
                        }
                    }

                    double amount = inputAmount(scanner);
                    manager.add(new Invoice(invoiceId, amount));
                    break;

                case 2:
                    if (manager.getSize() == 0) {
                        System.out.println("⚠️ Danh sách hóa đơn hiện đang trống.");
                        break;
                    }
                    manager.display();
                    int updateIndex = inputIndex(scanner, manager.getSize(), "sửa");

                    String newInvoiceId;
                    while (true) {
                        try {
                            System.out.print("Nhập mã hóa đơn mới: ");
                            newInvoiceId = scanner.nextLine().trim();
                            if (newInvoiceId.isEmpty()) {
                                throw new IllegalArgumentException("Mã hóa đơn không được để trống!");
                            }
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("❌ Lỗi: " + e.getMessage());
                        }
                    }

                    double newAmount = inputAmount(scanner);
                    manager.update(updateIndex, new Invoice(newInvoiceId, newAmount));
                    break;

                case 3:
                    if (manager.getSize() == 0) {
                        System.out.println("⚠️ Danh sách hóa đơn hiện đang trống.");
                        break;
                    }
                    manager.display();
                    int deleteIndex = inputIndex(scanner, manager.getSize(), "xóa");
                    manager.delete(deleteIndex);
                    break;

                case 4:
                    manager.display();
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

    private static double inputAmount(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Nhập số tiền: ");
                double amount = Double.parseDouble(scanner.nextLine());
                if (amount < 0) {
                    throw new IllegalArgumentException("Số tiền không được là số âm!");
                }
                return amount;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Số tiền phải là một số hợp lệ!");
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        }
    }

    private static int inputIndex(Scanner scanner, int size, String action) {
        while (true) {
            try {
                System.out.print("Nhập số thứ tự hóa đơn cần " + action + " (1 - " + size + "): ");
                int index = Integer.parseInt(scanner.nextLine()) - 1;
                if (index < 0 || index >= size) {
                    throw new IllegalArgumentException("Số thứ tự không tồn tại!");
                }
                return index;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Vui lòng nhập một số nguyên hợp lệ!");
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        }
    }
}
