import java.util.ArrayList;

public class InvoiceManager implements Manage<Invoice> {
    private ArrayList<Invoice> invoices = new ArrayList<>();

    public int getSize() {
        return invoices.size();
    }

    public boolean isIdExists(String id) {
        for (Invoice invoice : invoices) {
            if (invoice.getInvoiceId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(Invoice item) {
        invoices.add(item);
        System.out.println("✅ Thêm hóa đơn thành công!");
    }

    @Override
    public void update(int index, Invoice item) {
        if (index < 0 || index >= invoices.size()) {
            System.out.println("❌ Lỗi: Vị trí hóa đơn không hợp lệ!");
            return;
        }
        invoices.set(index, item);
        System.out.println("✅ Cập nhật hóa đơn thành công!");
    }

    @Override
    public void delete(int index) {
        if (index < 0 || index >= invoices.size()) {
            System.out.println("❌ Lỗi: Vị trí hóa đơn không hợp lệ!");
            return;
        }
        invoices.remove(index);
        System.out.println("✅ Xóa hóa đơn thành công!");
    }

    @Override
    public void display() {
        if (invoices.isEmpty()) {
            System.out.println("⚠️ Danh sách hóa đơn hiện đang trống.");
            return;
        }
        System.out.println("\n--- DANH SÁCH HÓA ĐƠN ---");
        for (int i = 0; i < invoices.size(); i++) {
            System.out.println((i + 1) + ". " + invoices.get(i));
        }
    }
}
