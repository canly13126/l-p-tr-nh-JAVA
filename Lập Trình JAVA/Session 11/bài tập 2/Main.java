import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Alice", 20, 8.5),
            new Student("Bob", 22, 6.0),
            new Student("Charlie", 21, 7.5),
            new Student("David", 23, 9.0),
            new Student("Eve", 20, 5.5),
            new Student("Frank", 22, 8.0),
            new Student("Grace", 21, 7.0),
            new Student("Hannah", 20, 9.5),
            new Student("Ivan", 24, 6.5),
            new Student("Jack", 22, 8.2)
        );

        List<Student> filteredAndSortedStudents = students.stream()
            .filter(student -> student.getGrade() > 7.0)
            .sorted(Comparator.comparing(Student::getName))
            .collect(Collectors.toList());

        System.out.println("Danh sách sinh viên có điểm lớn hơn 7.0 (đã sắp xếp theo tên):");
        filteredAndSortedStudents.forEach(System.out::println);
    }
}
