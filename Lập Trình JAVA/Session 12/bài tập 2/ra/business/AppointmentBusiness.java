package ra.business;

import ra.entity.Appointment;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AppointmentBusiness {
    private List<Appointment> appointments = new ArrayList<>();

    public void addAppointment(Scanner scanner) {
        Appointment appointment = new Appointment();
        appointment.inputData(scanner, appointments);
        appointments.add(appointment);
        System.out.println("✅ Thêm lịch hẹn thành công!");
    }

    public void displayAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("⚠️ Danh sách lịch hẹn hiện đang trống.");
            return;
        }
        System.out.println("\n--- DANH SÁCH LỊCH HẸN (SẮP XẾP THEO NGÀY HẸN TĂNG DẦN) ---");
        appointments.stream()
                .sorted(Comparator.comparing(Appointment::getAppointmentDate))
                .forEach(System.out::println);
    }

    public void searchByPatientName(Scanner scanner) {
        if (appointments.isEmpty()) {
            System.out.println("⚠️ Danh sách lịch hẹn hiện đang trống.");
            return;
        }
        System.out.print("Nhập tên bệnh nhân cần tìm: ");
        String keyword = scanner.nextLine().trim().toLowerCase();

        List<Appointment> results = appointments.stream()
                .filter(a -> a.getPatientName().toLowerCase().contains(keyword))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("❌ Không tìm thấy lịch hẹn nào cho bệnh nhân \"" + keyword + "\".");
        } else {
            System.out.println("\n--- KẾT QUẢ TÌM KIẾM ---");
            results.forEach(System.out::println);
        }
    }

    public void updateAppointment(Scanner scanner) {
        if (appointments.isEmpty()) {
            System.out.println("⚠️ Danh sách lịch hẹn hiện đang trống.");
            return;
        }
        System.out.print("Nhập mã lịch hẹn cần cập nhật: ");
        String id = scanner.nextLine().trim();

        Optional<Appointment> optionalAppointment = appointments.stream()
                .filter(a -> a.getAppointmentId().equalsIgnoreCase(id))
                .findFirst();

        optionalAppointment.ifPresentOrElse(
            appointment -> {
                System.out.println("Thông tin hiện tại: " + appointment);
                appointment.setPatientName(Appointment.inputPatientName(scanner));
                appointment.setPhoneNumber(Appointment.inputPhoneNumber(scanner));
                appointment.setAppointmentDate(Appointment.inputAppointmentDate(scanner));
                appointment.setDoctor(Appointment.inputDoctor(scanner));
                System.out.println("✅ Cập nhật lịch hẹn thành công!");
            },
            () -> System.out.println("❌ Không tìm thấy lịch hẹn có mã \"" + id + "\".")
        );
    }

    public void deleteAppointment(Scanner scanner) {
        if (appointments.isEmpty()) {
            System.out.println("⚠️ Danh sách lịch hẹn hiện đang trống.");
            return;
        }
        System.out.print("Nhập mã lịch hẹn cần xóa: ");
        String id = scanner.nextLine().trim();

        Optional<Appointment> optionalAppointment = appointments.stream()
                .filter(a -> a.getAppointmentId().equalsIgnoreCase(id))
                .findFirst();

        optionalAppointment.ifPresentOrElse(
            appointment -> {
                System.out.println("Tìm thấy lịch hẹn: " + appointment);
                System.out.print("Bạn có chắc chắn muốn xóa lịch hẹn này? (Y/N): ");
                String confirm = scanner.nextLine().trim();
                if (confirm.equalsIgnoreCase("Y")) {
                    appointments.remove(appointment);
                    System.out.println("✅ Đã xóa lịch hẹn thành công!");
                } else {
                    System.out.println("ℹ️ Đã hủy thao tác xóa.");
                }
            },
            () -> System.out.println("❌ Không tìm thấy lịch hẹn có mã \"" + id + "\".")
        );
    }

    public void statistics() {
        System.out.println("\n--- THỐNG KÊ LỊCH HẸN ---");
        System.out.println("Tổng số lịch hẹn: " + appointments.size());

        if (appointments.isEmpty()) {
            return;
        }

        System.out.println("Số lịch hẹn theo từng bác sĩ:");
        Map<String, Long> statsByDoctor = appointments.stream()
                .collect(Collectors.groupingBy(Appointment::getDoctor, Collectors.counting()));

        statsByDoctor.forEach((doctor, count) ->
                System.out.println("- Bác sĩ " + doctor + ": " + count + " lịch hẹn"));
    }
}
