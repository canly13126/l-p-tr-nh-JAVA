
interface IBank {
    void deposit(double amount);
    void withdraw(double amount);
}

class BankAccount implements IBank {
    private String accountId;
    private double balance;
    private String userName;
    private String phoneNumber;

    public BankAccount(String accountId, double balance,
                       String userName, String phoneNumber) {
        this.accountId = accountId;
        this.balance = balance >= 0 ? balance : 0;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void deposit(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println("Số tiền nạp không hợp lệ!");
            return;
        }

        balance += amount;
        System.out.printf("Nạp thành công: %,.0f VND%n", amount);
    }

    @Override
    public void withdraw(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println("Số tiền rút không hợp lệ!");
        } else if (amount > balance) {
            System.out.println("Số dư không đủ để thực hiện giao dịch!");
        } else {
            balance -= amount;
            System.out.printf("Rút thành công: %,.0f VND%n", amount);
        }
    }

    public void displayBalance() {
        System.out.println("Mã tài khoản: " + accountId);
        System.out.println("Chủ tài khoản: " + userName);
        System.out.println("Số điện thoại: " + phoneNumber);
        System.out.printf("Số dư: %,.0f VND%n", balance);
    }
}

public class bai4ss8 {
    public static void main(String[] args) {
        BankAccount taiKhoanA = new BankAccount(
                "TK001", 10000000, "Nguyen Van A", "0912345678"
        );

        BankAccount taiKhoanB = new BankAccount(
                "TK002", 5000000, "Tran Thi B", "0987654321"
        );

        System.out.println("===== SỐ DƯ BAN ĐẦU =====");
        taiKhoanA.displayBalance();
        System.out.println();
        taiKhoanB.displayBalance();

        double soTienChuyen = 3000000;

        System.out.println("\n===== CHUYỂN TIỀN TỪ A SANG B =====");

        if (soTienChuyen > 0 && soTienChuyen <= taiKhoanA.getBalance()) {
            taiKhoanA.withdraw(soTienChuyen);
            taiKhoanB.deposit(soTienChuyen);
            System.out.println("Chuyển tiền thành công!");
        } else {
            System.out.println("Chuyển tiền thất bại!");
        }

        System.out.println("\n===== SỐ DƯ SAU KHI CHUYỂN =====");

        System.out.println("Tài khoản A:");
        taiKhoanA.displayBalance();

        System.out.println();

        System.out.println("Tài khoản B:");
        taiKhoanB.displayBalance();
    }
}
