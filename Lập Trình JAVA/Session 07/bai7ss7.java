
import java.util.Scanner;

class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Mẫu số không được bằng 0!");
        }

        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
        simplify();
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Mẫu số không được bằng 0!");
        }

        this.denominator = denominator;
        simplify();
    }

    public Fraction add(Fraction other) {
        int tu = numerator * other.denominator
                + other.numerator * denominator;
        int mau = denominator * other.denominator;

        return new Fraction(tu, mau);
    }

    public Fraction subtract(Fraction other) {
        int tu = numerator * other.denominator
                - other.numerator * denominator;
        int mau = denominator * other.denominator;

        return new Fraction(tu, mau);
    }

    public Fraction multiply(Fraction other) {
        int tu = numerator * other.numerator;
        int mau = denominator * other.denominator;

        return new Fraction(tu, mau);
    }

    public Fraction divide(Fraction other) {
        if (other.numerator == 0) {
            throw new ArithmeticException("Không thể chia cho phân số bằng 0!");
        }

        int tu = numerator * other.denominator;
        int mau = denominator * other.numerator;

        return new Fraction(tu, mau);
    }

    public void simplify() {
        int a = Math.abs(numerator);
        int b = Math.abs(denominator);

        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        int gcd = a;

        if (gcd != 0) {
            numerator /= gcd;
            denominator /= gcd;
        }

        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}

public class bai7ss7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== NHẬP PHÂN SỐ THỨ NHẤT ===");

            System.out.print("Nhập tử số: ");
            int tu1 = scanner.nextInt();

            System.out.print("Nhập mẫu số: ");
            int mau1 = scanner.nextInt();

            Fraction ps1 = new Fraction(tu1, mau1);

            System.out.println("\n=== NHẬP PHÂN SỐ THỨ HAI ===");

            System.out.print("Nhập tử số: ");
            int tu2 = scanner.nextInt();

            System.out.print("Nhập mẫu số: ");
            int mau2 = scanner.nextInt();

            Fraction ps2 = new Fraction(tu2, mau2);

            System.out.println("\n=== KẾT QUẢ ===");
            System.out.println("Phân số thứ nhất: " + ps1);
            System.out.println("Phân số thứ hai: " + ps2);

            System.out.println("Tổng: " + ps1.add(ps2));
            System.out.println("Hiệu: " + ps1.subtract(ps2));
            System.out.println("Tích: " + ps1.multiply(ps2));
            System.out.println("Thương: " + ps1.divide(ps2));

        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }

        scanner.close();
    }
}
