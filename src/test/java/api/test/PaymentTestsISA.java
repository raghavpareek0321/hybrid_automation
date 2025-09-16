package api.test;

import api.endpoints.PaymentEndPoints;
import api.payload.PaymentPayload;
import api.config.ConfigManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PaymentTestsISA {

    int paymentId = 501;

    @Test(priority = 1, groups = {"isa"})
    public void testCreatePayment() {
        PaymentPayload payment = new PaymentPayload();
        payment.setPaymentId(paymentId);
        payment.setAmount(100.50);
        payment.setCurrency("USD");
        payment.setStatus("PENDING");

        Response response = PaymentEndPoints.createPayment(payment);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2, groups = {"isa"})
    public void testGetPayment() {
        Response response = PaymentEndPoints.readPayment(paymentId);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3, groups = {"isa"})
    public void testUpdatePayment() {
        PaymentPayload payment = new PaymentPayload();
        payment.setPaymentId(paymentId);
        payment.setAmount(150.75);
        payment.setCurrency("USD");
        payment.setStatus("COMPLETED");

        Response response = PaymentEndPoints.updatePayment(paymentId, payment);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 4, groups = {"isa"})
    public void testDeletePayment() {
        Response response = PaymentEndPoints.deletePayment(paymentId);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
