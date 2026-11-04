
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class bai10ss5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập đoạn văn bản: ");
        String vanBan = scanner.nextLine();

        if (vanBan.trim().isEmpty()) {
            System.out.println("Không có văn bản để xử lý.");
            scanner.close();
            return;
        }

        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(vanBan);

        ArrayList<String> danhSachSo = new ArrayList<>();

        while (matcher.find()) {
            danhSachSo.add(matcher.group());
        }

        String vanBanChuanHoa = vanBan.replaceAll("[^a-zA-Z0-9\\s]", "");
        vanBanChuanHoa = vanBanChuanHoa.toLowerCase();
        vanBanChuanHoa = vanBanChuanHoa.trim().replaceAll("\\s+", " ");

        System.out.println("Văn bản sau khi chuẩn hóa:");
        System.out.println(vanBanChuanHoa);

        System.out.println("Danh sách các số tìm được:");

        if (danhSachSo.isEmpty()) {
            System.out.println("Không có số nào trong văn bản.");
        } else {
            for (String so : danhSachSo) {
                System.out.println(so);
            }
        }

        scanner.close();
    }
}
