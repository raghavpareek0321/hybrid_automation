package api.test;

import api.payload.User;
import api.payload.UserPayloads;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class UserTests {

    private static final String BASE_URI = "https://petstore.swagger.io/v2";

    @Test(groups = {"smoke"})
    public void testCreateUser() {
        User user = UserPayloads.newValidUser();

        int status =
            given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(user)
            .when()
                .post("/user")
            .then()
                .extract()
                .statusCode();

        Assert.assertEquals(status, 200, "Failed to create user!");
    }

    @Test(groups = {"sanity"})
    public void testGetUserByName() {
        User user = UserPayloads.newValidUser();
        // First, create the user
        given()
            .baseUri(BASE_URI)
            .contentType(ContentType.JSON)
            .body(user)
        .when()
            .post("/user")
        .then()
            .statusCode(200);

        // Now, fetch the user
        int status =
            given()
                .baseUri(BASE_URI)
            .when()
                .get("/user/" + user.getUsername())
            .then()
                .extract()
                .statusCode();

        Assert.assertEquals(status, 200, "User not found after creation!");
    }

    @Test(groups = {"sanity"})
    public void testUpdateUser() {
        User user = UserPayloads.newValidUser();
        // Create user first
        given()
            .baseUri(BASE_URI)
            .contentType(ContentType.JSON)
            .body(user)
        .when()
            .post("/user")
        .then()
            .statusCode(200);

        // Update email
        User updated = UserPayloads.updateEmail(user, "updated_" + user.getEmail());

        int status =
            given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(updated)
            .when()
                .put("/user/" + user.getUsername())
            .then()
                .extract()
                .statusCode();

        Assert.assertEquals(status, 200, "Updated user not found!");
    }

    @Test(groups = {"smoke"})
    public void testDeleteUser() {
        User user = UserPayloads.newValidUser();
        // Create user
        given()
            .baseUri(BASE_URI)
            .contentType(ContentType.JSON)
            .body(user)
        .when()
            .post("/user")
        .then()
            .statusCode(200);

        // Delete user
        int status =
            given()
                .baseUri(BASE_URI)
            .when()
                .delete("/user/" + user.getUsername())
            .then()
                .extract()
                .statusCode();

        Assert.assertEquals(status, 200, "Failed to delete user!");
    }
}
