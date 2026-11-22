import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<Integer, String> romanMap = new LinkedHashMap<>();
        romanMap.put(1000, "M");
        romanMap.put(900, "CM");
        romanMap.put(500, "D");
        romanMap.put(400, "CD");
        romanMap.put(100, "C");
        romanMap.put(90, "XC");
        romanMap.put(50, "L");
        romanMap.put(40, "XL");
        romanMap.put(10, "X");
        romanMap.put(9, "IX");
        romanMap.put(5, "V");
        romanMap.put(4, "IV");
        romanMap.put(1, "I");

        int number;
        while (true) {
            try {
                System.out.print("Nhập một số nguyên dương N (1 - 3999): ");
                number = Integer.parseInt(scanner.nextLine());
                if (number < 1 || number > 3999) {
                    System.out.println("❌ Lỗi: Giá trị La Mã chỉ được quy định từ 1 đến 3999!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Vui lòng nhập một số nguyên hợp lệ!");
            }
        }

        String romanResult = convertToRoman(number, romanMap);
        System.out.println("✅ Số La Mã tương ứng của " + number + " là: " + romanResult);

        scanner.close();
    }

    private static String convertToRoman(int number, Map<Integer, String> romanMap) {
        StringBuilder result = new StringBuilder();
        int temp = number;

        for (Map.Entry<Integer, String> entry : romanMap.entrySet()) {
            int value = entry.getKey();
            String symbol = entry.getValue();

            while (temp >= value) {
                result.append(symbol);
                temp -= value;
            }
        }

        return result.toString();
    }
}
