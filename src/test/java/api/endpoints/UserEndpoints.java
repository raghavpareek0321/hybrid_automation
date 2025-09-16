package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.UserPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * Main User API Endpoints
 */
public class UserEndPoints {

    private static final String BASE_URL =
            System.getProperty("userApiBaseUrl", "https://petstore.swagger.io/v2");

    public static Response createUser(UserPayload payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
        .when()
                .post(BASE_URL + "/user");
    }

    public static Response getUser(String username) {
        return given()
                .pathParam("username", username)
        .when()
                .get(BASE_URL + "/user/{username}");
    }

    /** Backward compatibility for tests calling readUser() */
    public static Response readUser(String username) {
        return getUser(username);
    }

    public static Response updateUser(String username, UserPayload payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .body(payload)
        .when()
                .put(BASE_URL + "/user/{username}");
    }

    public static Response deleteUser(String username) {
        return given()
                .pathParam("username", username)
        .when()
                .delete(BASE_URL + "/user/{username}");
    }
}
