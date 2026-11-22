package ra.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Appointment {
    private String appointmentId;
    private String patientName;
    private String phoneNumber;
    private LocalDate appointmentDate;
    private String doctor;

    public Appointment() {
    }

    public Appointment(String appointmentId, String patientName, String phoneNumber, LocalDate appointmentDate, String doctor) {
        this.appointmentId = appointmentId;
        this.patientName = patientName;
        this.phoneNumber = phoneNumber;
        this.appointmentDate = appointmentDate;
        this.doctor = doctor;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void inputData(Scanner scanner) {
        inputData(scanner, new ArrayList<>());
    }

    public void inputData(Scanner scanner, List<Appointment> existingAppointments) {
        this.appointmentId = inputAppointmentId(scanner, existingAppointments);
        this.patientName = inputPatientName(scanner);
        this.phoneNumber = inputPhoneNumber(scanner);
        this.appointmentDate = inputAppointmentDate(scanner);
        this.doctor = inputDoctor(scanner);
    }

    public static String inputAppointmentId(Scanner scanner, List<Appointment> existingAppointments) {
        while (true) {
            System.out.print("Nhập mã lịch hẹn (đúng 6 ký tự): ");
            String id = scanner.nextLine().trim();
            if (id.length() != 6) {
                System.out.println("❌ Lỗi: Mã lịch hẹn phải gồm đúng 6 ký tự!");
                continue;
            }
            boolean isDuplicate = existingAppointments.stream()
                    .anyMatch(a -> a.getAppointmentId().equalsIgnoreCase(id));
            if (isDuplicate) {
                System.out.println("❌ Lỗi: Mã lịch hẹn này đã tồn tại!");
                continue;
            }
            return id;
        }
    }

    public static String inputPatientName(Scanner scanner) {
        while (true) {
            System.out.print("Nhập tên bệnh nhân (10 - 50 ký tự): ");
            String name = scanner.nextLine().trim();
            if (name.length() < 10 || name.length() > 50) {
                System.out.println("❌ Lỗi: Tên bệnh nhân phải có độ dài từ 10 đến 50 ký tự!");
                continue;
            }
            return name;
        }
    }

    public static String inputPhoneNumber(Scanner scanner) {
        String vnPhoneRegex = "^(03|05|07|08|09)\\d{8}$";
        while (true) {
            System.out.print("Nhập số điện thoại liên hệ (10 số di động VN): ");
            String phone = scanner.nextLine().trim();
            if (!phone.matches(vnPhoneRegex)) {
                System.out.println("❌ Lỗi: Số điện thoại không đúng định dạng di động Việt Nam!");
                continue;
            }
            return phone;
        }
    }

    public static LocalDate inputAppointmentDate(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            try {
                System.out.print("Nhập ngày hẹn khám (dd/MM/yyyy): ");
                String dateStr = scanner.nextLine().trim();
                return LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("❌ Lỗi: Ngày hẹn phải đúng định dạng dd/MM/yyyy!");
            }
        }
    }

    public static String inputDoctor(Scanner scanner) {
        while (true) {
            System.out.print("Nhập bác sĩ phụ trách (tối đa 200 ký tự): ");
            String doctor = scanner.nextLine().trim();
            if (doctor.isEmpty()) {
                System.out.println("❌ Lỗi: Tên bác sĩ phụ trách không được để trống!");
                continue;
            }
            if (doctor.length() > 200) {
                System.out.println("❌ Lỗi: Tên bác sĩ phụ trách không được vượt quá 200 ký tự!");
                continue;
            }
            return doctor;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("Mã LH: %-8s | Bệnh nhân: %-22s | SĐT: %-12s | Ngày hẹn: %-12s | Bác sĩ: %s",
                appointmentId, patientName, phoneNumber, appointmentDate.format(formatter), doctor);
    }
}
