
public class bai2ss5 {
    public static void main(String[] args) {
        StringBuilder chuoi = new StringBuilder("Hello, Java World!");

        System.out.println("Chuỗi ban đầu: " + chuoi);

        chuoi.delete(5, 10);
        System.out.println("Chuỗi sau khi xóa: " + chuoi);

        int viTri = chuoi.indexOf("World");

        if (viTri != -1) {
            chuoi.replace(viTri, viTri + 5, "Universe");
        }

        System.out.println("Chuỗi sau khi thay thế: " + chuoi);
    }
}
