import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println("Các số chẵn trong danh sách:");
        numbers.forEach(n -> {
            if (n % 2 == 0) {
                System.out.println(n);
            }
        });

        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println("Tổng của tất cả các số trong danh sách: " + sum);
    }
}
