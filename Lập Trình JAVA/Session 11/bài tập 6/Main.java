import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Chuột không dây", 45.0),
            new Product("Bàn phím cơ", 120.0),
            new Product("Tai nghe Bluetooth", 85.0),
            new Product("Màn hình 24 inch", 250.0),
            new Product("Lót chuột", 15.0)
        );

        ProductProcessor processor = new ProductProcessorImpl();

        if (processor.hasExpensiveProduct(products)) {
            System.out.println("--- CÁC SẢN PHẨM ĐẮT TIỀN (GIÁ > 100) ---");
            products.stream()
                    .filter(p -> p.getPrice() > 100)
                    .forEach(System.out::println);
        } else {
            System.out.println("Không có sản phẩm đắt tiền");
        }

        double totalValue = processor.calculateTotalValue(products);
        System.out.printf("\nTổng giá trị của tất cả sản phẩm: %.2f\n", totalValue);

        ProductProcessor.printProductList(products);
    }
}
