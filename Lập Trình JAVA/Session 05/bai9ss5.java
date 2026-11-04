
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class bai9ss5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập URL: ");
        String url = scanner.nextLine().trim();

        String regex = "^(https?)://([^/\\s?#]+)(/[^\\s]*)?$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);

        if (matcher.matches()) {
            String giaoThuc = matcher.group(1);
            String tenMien = matcher.group(2);
            String duongDan = matcher.group(3);

            if (duongDan == null) {
                duongDan = "";
            }

            System.out.println("URL hợp lệ");
            System.out.println("Giao thức: " + giaoThuc);
            System.out.println("Tên miền: " + tenMien);
            System.out.println("Đường dẫn: " + duongDan);
        } else {
            System.out.println("URL không hợp lệ");
        }

        scanner.close();
    }
}
