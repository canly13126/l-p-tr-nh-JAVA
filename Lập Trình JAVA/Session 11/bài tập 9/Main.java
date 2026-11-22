import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        User u1 = new User(1, "Quý", "quy@example.com");
        User u2 = new User(2, "Lan", null);
        User u3 = new User(3, "Huyền", "huyen@example.com");

        List<User> users = List.of(u1, u2, u3);

        List<Task> tasks = List.of(
            new Task(1, "Viết báo cáo", u1, LocalDate.of(2025, 3, 20), false),
            new Task(2, "Thiết kế slide", u1, LocalDate.of(2025, 3, 25), true),
            new Task(3, "Họp nhóm", u2, LocalDate.of(2025, 3, 18), false),
            new Task(4, "Nộp tài liệu", u3, LocalDate.of(2025, 3, 22), true),
            new Task(5, "Chuẩn bị thuyết trình", u2, LocalDate.of(2025, 3, 19), true)
        );

        LocalDate currentDate = LocalDate.of(2025, 3, 23);

        System.out.println("=== DANH SÁCH CÔNG VIỆC QUÁ HẠN VÀ CHƯA HOÀN THÀNH ===");
        tasks.stream()
                .filter(task -> !task.isCompleted() && task.getDueDate().isBefore(currentDate))
                .forEach(System.out::println);

        long completedCount = tasks.stream()
                .filter(Task::isCompleted)
                .count();
        System.out.println("\nTổng số công việc đã hoàn thành: " + completedCount + "/" + tasks.size());

        System.out.println("\n=== THỐNG KÊ CÔNG VIỆC THEO NGƯỜI DÙNG ===");
        Map<User, List<Task>> tasksByUser = tasks.stream()
                .collect(Collectors.groupingBy(Task::getAssignedTo));

        for (User user : users) {
            List<Task> userTasks = tasksByUser.getOrDefault(user, List.of());
            long done = userTasks.stream().filter(Task::isCompleted).count();
            System.out.printf("- %s | Tổng việc: %d | Hoàn thành: %d\n",
                    user, userTasks.size(), done);
        }
    }
}
