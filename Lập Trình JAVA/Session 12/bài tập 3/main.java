import ra.business.OrderBusiness;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        OrderBusiness orderBusiness = new OrderBusiness();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n*********************QUẢN LÝ ĐƠN HÀNG********************");
            System.out.println("1. Thêm đơn hàng");
            System.out.println("2. Hiển thị danh sách đơn hàng");
            System.out.println("3. Cập nhật trạng thái đơn hàng theo mã đơn hàng");
            System.out.println("4. Xóa đơn hàng theo mã đơn hàng");
            System.out.println("5. Tìm kiếm đơn hàng theo tên khách hàng");
            System.out.println("6. Thống kê tổng số đơn hàng");
            System.out.println("7. Thống kê tổng doanh thu các đơn hàng có trạng thái Delivered");
            System.out.println("8. Thống kê số lượng đơn hàng theo từng trạng thái");
            System.out.println("9. Tìm kiếm đơn hàng có giá trị lớn nhất");
            System.out.println("10. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Vui lòng chỉ nhập số từ 1 đến 10!");
                continue;
            }

            switch (choice) {
                case 1:
                    orderBusiness.addOrder(scanner);
                    break;
                case 2:
                    orderBusiness.displayOrders();
                    break;
                case 3:
                    orderBusiness.updateOrderStatus(scanner);
                    break;
                case 4:
                    orderBusiness.deleteOrder(scanner);
                    break;
                case 5:
                    orderBusiness.searchByCustomerName(scanner);
                    break;
                case 6:
                    orderBusiness.statisticsTotalOrders();
                    break;
                case 7:
                    orderBusiness.statisticsDeliveredRevenue();
                    break;
                case 8:
                    orderBusiness.statisticsCountByStatus();
                    break;
                case 9:
                    orderBusiness.findMaxAmountOrder();
                    break;
                case 10:
                    System.out.println("Đã thoát chương trình.");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ, vui lòng chọn từ 1 đến 10!");
            }
        }
    }
}
