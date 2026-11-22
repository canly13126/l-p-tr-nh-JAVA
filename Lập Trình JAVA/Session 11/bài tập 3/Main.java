import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Message> messages = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== ỨNG DỤNG CHAT ĐƠN GIẢN =====");
            System.out.println("1. Gửi tin nhắn");
            System.out.println("2. Xem toàn bộ lịch sử chat");
            System.out.println("3. Lọc tin nhắn theo người gửi");
            System.out.println("4. Lọc tin nhắn theo ngày");
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
                    sendMessage(scanner, messages);
                    break;
                case 2:
                    viewHistory(messages);
                    break;
                case 3:
                    filterBySender(scanner, messages);
                    break;
                case 4:
                    filterByDate(scanner, messages);
                    break;
                case 5:
                    System.out.println("Đã thoát ứng dụng chat.");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        }
    }

    private static void sendMessage(Scanner scanner, List<Message> messages) {
        String sender;
        while (true) {
            System.out.print("Nhập tên người gửi (hoặc gõ 'exit' để hủy): ");
            sender = scanner.nextLine().trim();
            if (sender.equalsIgnoreCase("exit")) {
                return;
            }
            if (!sender.isEmpty()) {
                break;
            }
            System.out.println("❌ Lỗi: Tên người gửi không được để trống!");
        }

        String content;
        while (true) {
            System.out.print("Nhập nội dung tin nhắn: ");
            content = scanner.nextLine().trim();
            if (!content.isEmpty()) {
                break;
            }
            System.out.println("❌ Lỗi: Nội dung tin nhắn không được để trống!");
        }

        messages.add(new Message(sender, content, LocalDateTime.now()));
        System.out.println("✅ Đã gửi tin nhắn!");
    }

    private static void viewHistory(List<Message> messages) {
        if (messages.isEmpty()) {
            System.out.println("⚠️ Lịch sử chat hiện đang trống.");
            return;
        }
        System.out.println("\n--- LỊCH SỬ CHAT ---");
        messages.forEach(System.out::println);
    }

    private static void filterBySender(Scanner scanner, List<Message> messages) {
        if (messages.isEmpty()) {
            System.out.println("⚠️ Lịch sử chat hiện đang trống.");
            return;
        }

        System.out.print("Nhập tên người gửi cần lọc: ");
        String targetSender = scanner.nextLine().trim();

        List<Message> filtered = messages.stream()
                .filter(m -> m.getSender().equalsIgnoreCase(targetSender))
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("❌ Không tìm thấy tin nhắn nào từ người gửi \"" + targetSender + "\".");
        } else {
            System.out.println("\n--- TIN NHẮN CỦA " + targetSender.toUpperCase() + " ---");
            filtered.forEach(System.out::println);
        }
    }

    private static void filterByDate(Scanner scanner, List<Message> messages) {
        if (messages.isEmpty()) {
            System.out.println("⚠️ Lịch sử chat hiện đang trống.");
            return;
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate targetDate;

        while (true) {
            try {
                System.out.print("Nhập ngày cần lọc (định dạng dd/MM/yyyy, ví dụ 23/11/2026): ");
                String dateInput = scanner.nextLine().trim();
                targetDate = LocalDate.parse(dateInput, dateFormatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("❌ Lỗi: Định dạng ngày không hợp lệ! Vui lòng nhập đúng dạng dd/MM/yyyy.");
            }
        }

        LocalDate finalTargetDate = targetDate;
        List<Message> filtered = messages.stream()
                .filter(m -> m.getTimestamp().toLocalDate().equals(finalTargetDate))
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("❌ Không có tin nhắn nào trong ngày " + targetDate.format(dateFormatter) + ".");
        } else {
            System.out.println("\n--- TIN NHẮN NGÀY " + targetDate.format(dateFormatter) + " ---");
            filtered.forEach(System.out::println);
        }
    }
}
