
abstract class PaymentMethod {
    protected double amount;

    public PaymentMethod(double amount) {
        this.amount = amount;
    }

    public abstract double processPayment();

    public abstract double calculateFee();

    public abstract String processPayment(String currency);
}

class CreditCard extends PaymentMethod {
    public CreditCard(double amount) {
        super(amount);
    }

    @Override
    public double calculateFee() {
        return amount * 0.02;
    }

    @Override
    public double processPayment() {
        return amount + calculateFee();
    }

    @Override
    public String processPayment(String currency) {
        return String.format("%,.2f %s", processPayment(), currency);
    }
}

class DebitCard extends PaymentMethod {
    public DebitCard(double amount) {
        super(amount);
    }

    @Override
    public double calculateFee() {
        return amount * 0.01;
    }

    @Override
    public double processPayment() {
        return amount + calculateFee();
    }

    @Override
    public String processPayment(String currency) {
        return String.format("%,.2f %s", processPayment(), currency);
    }
}

class Cash extends PaymentMethod {
    public Cash(double amount) {
        super(amount);
    }

    @Override
    public double calculateFee() {
        return 0;
    }

    @Override
    public double processPayment() {
        return amount + calculateFee();
    }

    @Override
    public String processPayment(String currency) {
        return String.format("%,.2f %s", processPayment(), currency);
    }
}

public class bai10ss8 {
    public static void main(String[] args) {
        PaymentMethod[] payments = {
                new CreditCard(1000000),
                new DebitCard(1000000),
                new Cash(1000000)
        };

        String[] names = {
                "Credit Card",
                "Debit Card",
                "Cash"
        };

        System.out.println("========== HỆ THỐNG THANH TOÁN ==========");

        for (int i = 0; i < payments.length; i++) {
            System.out.println("\n--- " + names[i] + " ---");

            System.out.printf("Số tiền ban đầu: %,.2f VND%n",
                    payments[i].amount);

            System.out.printf("Phí giao dịch: %,.2f VND%n",
                    payments[i].calculateFee());

            System.out.printf("Tổng tiền thanh toán: %,.2f VND%n",
                    payments[i].processPayment());

            System.out.println("Thanh toán với đơn vị tiền tệ: "
                    + payments[i].processPayment("VND"));
        }
    }
}
