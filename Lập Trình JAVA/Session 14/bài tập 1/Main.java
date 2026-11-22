import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();

        List<Student> studentList = Arrays.asList(
            new Student("Nguyen Van An", 20),
            new Student("Tran Thi Bich", 21),
            new Student("Le Hoang Nam", 19)
        );

        studentManager.addStudents(studentList);
    }
}
