package api.test;

import api.endpoints.UserEndPoints;
import api.payload.UserPayload;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {

    private UserPayload user;
    private String username;

    @BeforeClass
    public void setup() {
        long ts = System.currentTimeMillis();
        username = "user_tests_" + ts;

        user = new UserPayload();
        user.setId((int) (ts % 1000000));
        user.setUsername(username);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe+" + ts + "@example.com");
        user.setPassword("Password123!");
        user.setPhone("9876543210");
    }

    @Test(priority = 1)
    public void testCreateUser() {
        Response res = UserEndPoints.createUser(user);
        res.then().statusCode(200);
    }

    @Test(priority = 2, dependsOnMethods = "testCreateUser")
    public void testGetUser() {
        Response res = UserEndPoints.getUser(username);
        res.then().statusCode(200);
    }

    @Test(priority = 3, dependsOnMethods = "testCreateUser")
    public void testUpdateUser() {
        user.setFirstName("Johnny");
        Response res = UserEndPoints.updateUser(username, user);
        res.then().statusCode(200);
    }

    @Test(priority = 4, dependsOnMethods = "testCreateUser")
    public void testDeleteUser() {
        Response res = UserEndPoints.deleteUser(username);
        res.then().statusCode(200);
    }
}
