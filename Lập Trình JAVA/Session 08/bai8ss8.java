
class Product {
    protected String name;
    protected double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getDiscount() {
        return 0;
    }

    public double getFinalPrice() {
        return price * (1 - getDiscount() / 100);
    }

    public double getDiscount(int quantity) {
        if (quantity > 100) {
            return 5;
        }
        return 0;
    }

    public double getFinalPrice(int quantity) {
        if (quantity <= 0) {
            return 0;
        }

        double discount = getDiscount();

        if (quantity > 100) {
            discount += getDiscount(quantity);
        }

        return price * quantity * (1 - discount / 100);
    }

    public void display() {
        System.out.println("Tên sản phẩm: " + name);
        System.out.printf("Giá gốc: %,.0f VND%n", price);
        System.out.println("Chiết khấu: " + getDiscount() + "%");
        System.out.printf("Giá sau chiết khấu: %,.0f VND%n", getFinalPrice());
    }
}

class Electronics extends Product {
    public Electronics(String name, double price) {
        super(name, price);
    }

    @Override
    public double getDiscount() {
        return 10;
    }
}

class Clothing extends Product {
    public Clothing(String name, double price) {
        super(name, price);
    }

    @Override
    public double getDiscount() {
        return 20;
    }
}

class Food extends Product {
    public Food(String name, double price) {
        super(name, price);
    }

    @Override
    public double getDiscount() {
        return 5;
    }
}

public class bai8ss8 {
    public static void main(String[] args) {
        Product[] danhSach = {
                new Product("Sản phẩm thông thường", 1000),
                new Electronics("Điện thoại", 10000000),
                new Clothing("Áo thun", 200000),
                new Food("Bánh mì", 20000)
        };

        int quantity = 120;

        System.out.println("===== HỆ THỐNG BÁN HÀNG =====");

        for (Product product : danhSach) {
            System.out.println("\n------------------------------");
            product.display();

            System.out.println("Số lượng mua: " + quantity);
            System.out.println("Chiết khấu theo số lượng: "
                    + product.getDiscount(quantity) + "%");

            System.out.printf("Tổng tiền sau chiết khấu: %,.0f VND%n",
                    product.getFinalPrice(quantity));
        }
    }
}
