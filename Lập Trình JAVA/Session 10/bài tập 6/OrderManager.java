import java.util.ArrayList;

public class OrderManager implements Manage<Order> {
    private ArrayList<Order> orders = new ArrayList<>();

    public int getSize() {
        return orders.size();
    }

    public int findIndexById(String orderId) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderId().equalsIgnoreCase(orderId)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void add(Order item) {
        orders.add(item);
        System.out.println("✅ Thêm đơn hàng thành công!");
    }

    @Override
    public void update(int index, Order item) {
        if (index < 0 || index >= orders.size()) {
            System.out.println("❌ Lỗi: Vị trí đơn hàng không hợp lệ!");
            return;
        }
        orders.set(index, item);
        System.out.println("✅ Cập nhật đơn hàng thành công!");
    }

    @Override
    public void delete(int index) {
        if (index < 0 || index >= orders.size()) {
            System.out.println("❌ Lỗi: Vị trí đơn hàng không hợp lệ!");
            return;
        }
        orders.remove(index);
        System.out.println("✅ Xóa đơn hàng thành công!");
    }

    @Override
    public void display() {
        if (orders.isEmpty()) {
            System.out.println("⚠️ Danh sách đơn hàng hiện đang trống.");
            return;
        }
        System.out.println("\n--- DANH SÁCH ĐƠN HÀNG ---");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println((i + 1) + ". " + orders.get(i));
        }
    }
}
