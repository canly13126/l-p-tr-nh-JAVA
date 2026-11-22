import java.util.ArrayList;

public class AttendanceManager implements Manage<Student> {
    private ArrayList<Student> students = new ArrayList<>();

    public int getSize() {
        return students.size();
    }

    @Override
    public void add(Student item) {
        students.add(item);
        System.out.println("✅ Thêm sinh viên thành công!");
    }

    @Override
    public void update(int index, Student item) {
        if (index < 0 || index >= students.size()) {
            System.out.println("❌ Lỗi: Vị trí không hợp lệ!");
            return;
        }
        students.set(index, item);
        System.out.println("✅ Cập nhật sinh viên thành công!");
    }

    @Override
    public void delete(int index) {
        if (index < 0 || index >= students.size()) {
            System.out.println("❌ Lỗi: Vị trí không hợp lệ!");
            return;
        }
        students.remove(index);
        System.out.println("✅ Xóa sinh viên thành công!");
    }

    @Override
    public void display() {
        if (students.isEmpty()) {
            System.out.println("⚠️ Danh sách điểm danh hiện đang trống.");
            return;
        }
        System.out.println("\n--- DANH SÁCH ĐIỂM DANH ---");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }
    }
}
import java.util.ArrayList;

public class AttendanceManager implements Manage<Student> {
    private ArrayList<Student> students = new ArrayList<>();

    public int getSize() {
        return students.size();
    }

    @Override
    public void add(Student item) {
        students.add(item);
        System.out.println("✅ Thêm sinh viên thành công!");
    }

    @Override
    public void update(int index, Student item) {
        if (index < 0 || index >= students.size()) {
            System.out.println("❌ Lỗi: Vị trí không hợp lệ!");
            return;
        }
        students.set(index, item);
        System.out.println("✅ Cập nhật sinh viên thành công!");
    }

    @Override
    public void delete(int index) {
        if (index < 0 || index >= students.size()) {
            System.out.println("❌ Lỗi: Vị trí không hợp lệ!");
            return;
        }
        students.remove(index);
        System.out.println("✅ Xóa sinh viên thành công!");
    }

    @Override
    public void display() {
        if (students.isEmpty()) {
            System.out.println("⚠️ Danh sách điểm danh hiện đang trống.");
            return;
        }
        System.out.println("\n--- DANH SÁCH ĐIỂM DANH ---");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }
    }
}
