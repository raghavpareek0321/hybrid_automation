package api.test;

import api.payload.User;
import api.payload.UserPayloads;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class UserTests2 {

    private static final String BASE_URI = "https://petstore.swagger.io/v2";

    @Test(groups = {"smoke"})
    public void testCreateMultipleUsers() {
        User user1 = UserPayloads.newValidUser("A");
        User user2 = UserPayloads.newValidUser("B");

        int status1 =
            given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(user1)
            .when()
                .post("/user")
            .then()
                .extract()
                .statusCode();

        int status2 =
            given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(user2)
            .when()
                .post("/user")
            .then()
                .extract()
                .statusCode();

        Assert.assertEquals(status1, 200, "Failed to create user1!");
        Assert.assertEquals(status2, 200, "Failed to create user2!");
    }

    @Test(groups = {"sanity"})
    public void testGetAllUsers() {
        // For PetStore Swagger, there’s no real endpoint for "all users",
        // so we simulate by creating one and fetching it by username.
        User user = UserPayloads.newValidUser();

        given()
            .baseUri(BASE_URI)
            .contentType(ContentType.JSON)
            .body(user)
        .when()
            .post("/user")
        .then()
            .statusCode(200);

        int status =
            given()
                .baseUri(BASE_URI)
            .when()
                .get("/user/" + user.getUsername())
            .then()
                .extract()
                .statusCode();

        Assert.assertEquals(status, 200, "User not found!");
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

        // Update first/last name
        User updated = UserPayloads.updateName(user, "UpdatedFirst", "UpdatedLast");

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

        Assert.assertEquals(status, 200, "Failed to update user!");
    }

    @Test(groups = {"smoke"})
    public void testDeleteUser() {
        User user = UserPayloads.newValidUser();

        given()
            .baseUri(BASE_URI)
            .contentType(ContentType.JSON)
            .body(user)
        .when()
            .post("/user")
        .then()
            .statusCode(200);

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
