package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.PaymentPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PaymentEndPoints {

    private static final String BASE_URL =
            System.getProperty("paymentApiBaseUrl",
                    System.getProperty("userApiBaseUrl", "https://petstore.swagger.io/v2"));

    public static Response createPayment(PaymentPayload payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
        .when()
                .post(BASE_URL + "/payment");
    }

    public static Response readPayment(int paymentId) {
        return given()
                .pathParam("paymentId", paymentId)
        .when()
                .get(BASE_URL + "/payment/{paymentId}");
    }

    public static Response updatePayment(int paymentId, PaymentPayload payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("paymentId", paymentId)
                .body(payload)
        .when()
                .put(BASE_URL + "/payment/{paymentId}");
    }

    public static Response deletePayment(int paymentId) {
        return given()
                .pathParam("paymentId", paymentId)
        .when()
                .delete(BASE_URL + "/payment/{paymentId}");
    }
}
