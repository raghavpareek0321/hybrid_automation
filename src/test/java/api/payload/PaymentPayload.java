package api.payload;

public class PaymentPayload {
    private int paymentId;
    private double amount;
    private String currency;
    private String status;

    // No-args constructor
    public PaymentPayload() {}

    // Full constructor
    public PaymentPayload(int paymentId, double amount, String currency, String status) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
    }

    // Getters + Setters
    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
