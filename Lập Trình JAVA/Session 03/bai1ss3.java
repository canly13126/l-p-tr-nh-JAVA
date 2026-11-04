
import java.util.Scanner;

public class bai1ss3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập tên khách hàng: ");
        String tenKhachHang = scanner.nextLine();

        System.out.print("Nhập tên sản phẩm: ");
        String tenSanPham = scanner.nextLine();

        System.out.print("Nhập giá sản phẩm: ");
        double gia = scanner.nextDouble();

        System.out.print("Nhập số lượng mua: ");
        int soLuong = scanner.nextInt();

        System.out.print("Khách hàng có thẻ thành viên (true/false): ");
        boolean thanhVien = scanner.nextBoolean();

        double thanhTien = gia * soLuong;
        double giamGia = thanhVien ? thanhTien * 0.10 : 0;
        double vat = (thanhTien - giamGia) * 0.08;
        double tongThanhToan = thanhTien - giamGia + vat;

        System.out.println("\n========== HÓA ĐƠN SIÊU THỊ ==========");
        System.out.println("Khách hàng: " + tenKhachHang);
        System.out.println("Sản phẩm: " + tenSanPham);
        System.out.println("Số lượng: " + soLuong);
        System.out.printf("Đơn giá: %.2f%n", gia);
        System.out.printf("Thành tiền: %.2f%n", thanhTien);
        System.out.printf("Giảm giá: %.2f%n", giamGia);
        System.out.printf("Tiền VAT (8%%): %.2f%n", vat);
        System.out.printf("Tổng thanh toán: %.2f%n", tongThanhToan);
        System.out.println("======================================");

        scanner.close();
    }
}
