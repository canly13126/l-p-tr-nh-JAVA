
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private int age;
    private String studentClass;
    private double avgScore;

    public Student(String name, int age, String studentClass, double avgScore) {
        this.name = name;
        this.age = age;
        this.studentClass = studentClass;
        this.avgScore = avgScore;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void display() {
        System.out.println("Họ tên: " + name);
        System.out.println("Tuổi: " + age);
        System.out.println("Lớp: " + studentClass);
        System.out.println("Điểm trung bình: " + avgScore);
        System.out.println("---------------------------");
    }
}

public class bai4ss7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Student> danhSach = new ArrayList<>();

        danhSach.add(new Student("Nguyễn Văn An", 20, "Java01", 8.5));
        danhSach.add(new Student("Trần Thị Bình", 21, "Java02", 7.2));
        danhSach.add(new Student("Lê Văn Cường", 19, "Java01", 6.5));
        danhSach.add(new Student("Phạm Thị Dung", 20, "Java03", 9.0));
        danhSach.add(new Student("Hoàng Văn Minh", 22, "Java02", 5.5));

        System.out.print("Nhập điểm min: ");
        double min = scanner.nextDouble();

        System.out.print("Nhập điểm max: ");
        double max = scanner.nextDouble();

        if (min > max) {
            System.out.println("Khoảng điểm không hợp lệ!");
            scanner.close();
            return;
        }

        System.out.println("\n=== DANH SÁCH SINH VIÊN CÓ ĐIỂM TỪ "
                + min + " ĐẾN " + max + " ===");

        boolean timThay = false;

        for (Student student : danhSach) {
            if (student.getAvgScore() >= min && student.getAvgScore() <= max) {
                student.display();
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("Không có sinh viên nào trong khoảng điểm này!");
        }

        scanner.close();
    }
}
