
class Employee {
    private String name;
    private String id;
    private double salary;

    public Employee() {
    }

    public Employee(String name, String id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (Double.isFinite(salary) && salary >= 0) {
            this.salary = salary;
        }
    }

    public void increaseSalary(double amount) {
        if (Double.isFinite(amount) && amount > 0) {
            salary += amount;
        } else {
            System.out.println("Số tiền tăng lương không hợp lệ!");
        }
    }

    @Override
    public String toString() {
        return "Tên nhân viên: " + name
                + "\nMã nhân viên: " + id
                + "\nLương cơ bản: " + salary;
    }
}

class Manager extends Employee {
    private double bonus;

    public Manager() {
    }

    public Manager(String name, String id, double salary, double bonus) {
        super(name, id, salary);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public double getSalary() {
        return super.getSalary() + bonus;
    }

    @Override
    public String toString() {
        return "=== QUẢN LÝ ==="
                + "\nTên: " + getName()
                + "\nMã nhân viên: " + getId()
                + "\nLương cơ bản: " + super.getSalary()
                + "\nTiền thưởng: " + bonus
                + "\nTổng lương: " + getSalary();
    }
}

class Developer extends Employee {
    private String programmingLanguage;

    public Developer() {
    }

    public Developer(String name, String id, double salary,
                     String programmingLanguage) {
        super(name, id, salary);
        this.programmingLanguage = programmingLanguage;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    @Override
    public double getSalary() {
        return super.getSalary();
    }

    @Override
    public String toString() {
        return "=== LẬP TRÌNH VIÊN ==="
                + "\nTên: " + getName()
                + "\nMã nhân viên: " + getId()
                + "\nLương cơ bản: " + getSalary()
                + "\nNgôn ngữ lập trình: " + programmingLanguage;
    }
}

public class bai7ss8 {
    public static void main(String[] args) {
        Employee employee = new Employee(
                "Nguyen Van An", "NV001", 10000000
        );

        Manager manager = new Manager(
                "Tran Thi Binh", "QL001", 20000000, 5000000
        );

        Developer developer = new Developer(
                "Le Van Cuong", "DEV001", 15000000, "Java"
        );

        System.out.println("===== THÔNG TIN NHÂN VIÊN =====");

        System.out.println(employee);
        System.out.printf("Lương nhân viên: %,.0f VND%n", employee.getSalary());

        System.out.println("\n" + manager);
        System.out.printf("Lương quản lý: %,.0f VND%n", manager.getSalary());

        System.out.println("\n" + developer);
        System.out.printf("Lương lập trình viên: %,.0f VND%n", developer.getSalary());

        System.out.println("\n===== TĂNG LƯƠNG NHÂN VIÊN =====");

        employee.increaseSalary(2000000);

        System.out.println("Thông tin sau khi tăng lương:");
        System.out.println(employee);
        System.out.printf("Lương mới: %,.0f VND%n", employee.getSalary());
    }
}
