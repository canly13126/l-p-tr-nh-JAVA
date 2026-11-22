import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Event> events = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        while (true) {
            String name;
            while (true) {
                System.out.print("Nhập tên sự kiện (hoặc 'exit' để thoát): ");
                name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    System.out.println("Can not enter empty string");
                    continue;
                }
                break;
            }

            if (name.equalsIgnoreCase("exit")) {
                break;
            }

            LocalDateTime startDate;
            while (true) {
                try {
                    System.out.print("Nhập thời gian bắt đầu (dd-MM-yyyy HH:mm): ");
                    String startInput = scanner.nextLine().trim();
                    startDate = LocalDateTime.parse(startInput, formatter);
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Enter not valid date");
                }
            }

            LocalDateTime endDate;
            while (true) {
                try {
                    System.out.print("Nhập thời gian kết thúc (dd-MM-yyyy HH:mm): ");
                    String endInput = scanner.nextLine().trim();
                    endDate = LocalDateTime.parse(endInput, formatter);
                    if (endDate.isBefore(startDate)) {
                        System.out.println("End date must be after start date");
                        continue;
                    }
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Enter not valid date");
                }
            }

            events.add(new Event(name, startDate, endDate));
        }

        System.out.println("\nDanh sách sự kiện:");
        if (events.isEmpty()) {
            System.out.println("Không có sự kiện nào.");
        } else {
            for (Event event : events) {
                System.out.println(event);
            }
        }

        scanner.close();
    }
}
