package ra.entity;

import java.util.Scanner;

public class Order {
    private static int autoId = 1;
    private int orderId;
    private String customerName;
    private String phoneNumber;
    private String address;
    private float orderAmount;
    private String status;

    public Order() {
        this.orderId = autoId++;
        this.status = "Pending";
    }

    public Order(int orderId, String customerName, String phoneNumber, String address, float orderAmount, String status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.orderAmount = orderAmount;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(float orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void inputData(Scanner scanner) {
        this.customerName = inputCustomerName(scanner);
        this.phoneNumber = inputPhoneNumber(scanner);
        this.address = inputAddress(scanner);
        this.orderAmount = inputOrderAmount(scanner);
        this.status = "Pending";
    }

    public static String inputCustomerName(Scanner scanner) {
        while (true) {
            System.out.print("Nhập tên khách hàng (6 - 100 ký tự): ");
            String name = scanner.nextLine().trim();
            if (name.length() < 6 || name.length() > 100) {
                System.out.println("❌ Lỗi: Tên khách hàng phải có độ dài từ 6 đến 100 ký tự!");
                continue;
            }
            return name;
        }
    }

    public static String inputPhoneNumber(Scanner scanner) {
        String vnPhoneRegex = "^(03|05|07|08|09)\\d{8}$";
        while (true) {
            System.out.print("Nhập số điện thoại (10 số di động VN): ");
            String phone = scanner.nextLine().trim();
            if (!phone.matches(vnPhoneRegex)) {
                System.out.println("❌ Lỗi: Số điện thoại không đúng định dạng di động Việt Nam!");
                continue;
            }
            return phone;
        }
    }

    public static String inputAddress(Scanner scanner) {
        while (true) {
            System.out.print("Nhập địa chỉ giao hàng: ");
            String address = scanner.nextLine().trim();
            if (address.isEmpty()) {
                System.out.println("❌ Lỗi: Địa chỉ giao hàng không được để trống!");
                continue;
            }
            return address;
        }
    }

    public static float inputOrderAmount(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Nhập giá trị đơn hàng (> 0): ");
                float amount = Float.parseFloat(scanner.nextLine());
                if (amount <= 0) {
                    System.out.println("❌ Lỗi: Giá trị đơn hàng phải lớn hơn 0!");
                    continue;
                }
                return amount;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Giá trị đơn hàng phải là một số thực hợp lệ!");
            }
        }
    }

    @Override
    public String toString() {
        return String.format("Mã ĐH: %-5d | Khách hàng: %-22s | SĐT: %-12s | Địa chỉ: %-22s | Giá trị: %-10.2f | Trạng thái: %s",
                orderId, customerName, phoneNumber, address, orderAmount, status);
    }
}
