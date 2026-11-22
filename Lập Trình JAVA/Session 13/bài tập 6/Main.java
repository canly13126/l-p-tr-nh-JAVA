import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManagement taskManagement = new TaskManagement();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== HỆ THỐNG QUẢN LÝ TO-DO LIST =====");
            System.out.println("1. Thêm công việc");
            System.out.println("2. Liệt kê công việc");
            System.out.println("3. Cập nhật trạng thái công việc");
            System.out.println("4. Xóa công việc");
            System.out.println("5. Tìm kiếm công việc theo tên");
            System.out.println("6. Thống kê công việc");
            System.out.println("7. Thoát");
            System.out.print("Chọn chức năng (1-7): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Vui lòng chỉ nhập số từ 1 đến 7!");
                continue;
            }

            switch (choice) {
                case 1:
                    String taskName = inputNonEmptyString(scanner, "Nhập tên công việc: ", "Tên công việc");
                    String status = inputStatus(scanner);
                    taskManagement.addTask(taskName, status);
                    break;

                case 2:
                    taskManagement.listTasks();
                    break;

                case 3:
                    int updateId = inputPositiveInt(scanner, "Nhập ID công việc cần cập nhật: ", "ID công việc");
                    String newStatus = inputStatus(scanner);
                    taskManagement.updateTaskStatus(updateId, newStatus);
                    break;

                case 4:
                    int deleteId = inputPositiveInt(scanner, "Nhập ID công việc cần xóa: ", "ID công việc");
                    taskManagement.deleteTask(deleteId);
                    break;

                case 5:
                    String searchKeyword = inputNonEmptyString(scanner, "Nhập tên công việc cần tìm: ", "Từ khóa tìm kiếm");
                    taskManagement.searchTaskByName(searchKeyword);
                    break;

                case 6:
                    taskManagement.taskStatistics();
                    break;

                case 7:
                    System.out.println("Đã thoát chương trình.");
                    scanner.close();
                    return;

                default:
                    System.out.println("❌ Lựa chọn không hợp lệ, vui lòng chọn từ 1 đến 7!");
            }
        }
    }

    private static String inputNonEmptyString(Scanner scanner, String prompt, String fieldName) {
        while (true) {
            try {
                System.out.print(prompt);
                String value = scanner.nextLine().trim();
                if (value.isEmpty()) {
                    throw new IllegalArgumentException(fieldName + " không được để trống!");
                }
                return value;
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        }
    }

    private static String inputStatus(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Nhập trạng thái (chưa hoàn thành / đã hoàn thành): ");
                String status = scanner.nextLine().trim().toLowerCase();
                if (status.isEmpty()) {
                    throw new IllegalArgumentException("Trạng thái không được để trống!");
                }
                if (!status.equals("chưa hoàn thành") && !status.equals("đã hoàn thành")) {
                    throw new IllegalArgumentException("Trạng thái chỉ được nhập 'chưa hoàn thành' hoặc 'đã hoàn thành'!");
                }
                return status;
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        }
    }

    private static int inputPositiveInt(Scanner scanner, String prompt, String fieldName) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    throw new IllegalArgumentException(fieldName + " không được để trống!");
                }
                int value = Integer.parseInt(input);
                if (value <= 0) {
                    throw new IllegalArgumentException(fieldName + " phải là số nguyên dương!");
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: " + fieldName + " phải là một số nguyên hợp lệ!");
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        }
    }
}
