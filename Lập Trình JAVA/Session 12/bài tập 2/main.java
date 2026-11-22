import ra.business.AppointmentBusiness;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AppointmentBusiness appointmentBusiness = new AppointmentBusiness();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n*********************QUẢN LÝ LỊCH HẸN********************");
            System.out.println("1. Thêm lịch hẹn");
            System.out.println("2. Hiển thị danh sách lịch hẹn");
            System.out.println("3. Tìm kiếm lịch hẹn theo tên bệnh nhân");
            System.out.println("4. Cập nhật lịch hẹn theo mã lịch hẹn");
            System.out.println("5. Xóa lịch hẹn theo mã lịch hẹn");
            System.out.println("6. Thống kê");
            System.out.println("7. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Vui lòng chỉ nhập số từ 1 đến 7!");
                continue;
            }

            switch (choice) {
                case 1:
                    appointmentBusiness.addAppointment(scanner);
                    break;
                case 2:
                    appointmentBusiness.displayAppointments();
                    break;
                case 3:
                    appointmentBusiness.searchByPatientName(scanner);
                    break;
                case 4:
                    appointmentBusiness.updateAppointment(scanner);
                    break;
                case 5:
                    appointmentBusiness.deleteAppointment(scanner);
                    break;
                case 6:
                    appointmentBusiness.statistics();
                    break;
                case 7:
                    System.out.println("Đã thoát chương trình.");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ, vui lòng chọn từ 1 đến 7!");
            }
        }
    }
}
