
public class bai5ss5 {
    public static void main(String[] args) {
        int soLanLap = 1000000;

        long batDau = System.currentTimeMillis();

        String chuoi = "Hello";
        for (int i = 0; i < soLanLap; i++) {
            chuoi += " World";
        }

        long ketThuc = System.currentTimeMillis();
        long thoiGianString = ketThuc - batDau;

        batDau = System.currentTimeMillis();

        StringBuilder builder = new StringBuilder("Hello");
        for (int i = 0; i < soLanLap; i++) {
            builder.append(" World");
        }

        ketThuc = System.currentTimeMillis();
        long thoiGianBuilder = ketThuc - batDau;

        batDau = System.currentTimeMillis();

        StringBuffer buffer = new StringBuffer("Hello");
        for (int i = 0; i < soLanLap; i++) {
            buffer.append(" World");
        }

        ketThuc = System.currentTimeMillis();
        long thoiGianBuffer = ketThuc - batDau;

        System.out.println("===== SO SÁNH HIỆU SUẤT =====");
        System.out.println("String: " + thoiGianString + " ms");
        System.out.println("StringBuilder: " + thoiGianBuilder + " ms");
        System.out.println("StringBuffer: " + thoiGianBuffer + " ms");

        System.out.println("\n===== NHẬN XÉT =====");
        System.out.println("String: Chậm khi nối chuỗi nhiều lần vì tạo đối tượng mới.");
        System.out.println("StringBuilder: Nhanh, phù hợp khi xử lý chuỗi trong một luồng.");
        System.out.println("StringBuffer: Hỗ trợ đồng bộ, phù hợp khi làm việc với nhiều luồng.");
    }
}
