package api.endpoints;

import static io.restassured.RestAssured.*;
import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints2 {

    // Load the routes from the properties file
    static ResourceBundle getURL() {
        return ResourceBundle.getBundle("routes");
    }

    // Create a new user
    public static Response createUser(User payload) {
        String postUrl = getURL().getString("post_url"); 
        Response res = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
        .when()
                .post(postUrl);
        return res;
    }

    // Get user by username
    public static Response getUser(String userName) {
        String getUrl = getURL().getString("get_url"); 
        Response res = given()
                .pathParam("username", userName)
        .when()
                .get(getUrl);
        return res;
    }

    // Update user
    public static Response updateUser(User payload, String userName) {
        String putUrl = getURL().getString("update_url"); 
        Response res = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .pathParam("username", userName)
        .when()
                .put(putUrl);
        return res;
    }

    // Delete user
    public static Response deleteUser(String userName) {
        String deleteUrl = getURL().getString("delete_url"); 
        Response res = given()
                .pathParam("username", userName)
        .when()
                .delete(deleteUrl);
        return res;
    }
}
