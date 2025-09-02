package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints2;
import api.payload.User;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserTests2 {

    private Faker faker;
    private User userPayload;
    private static final Logger logger = LogManager.getLogger(UserTests2.class);

    @BeforeClass
    public void setUp() {
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.number().numberBetween(10000, 99999));
        userPayload.setUsername("user" + faker.number().numberBetween(1000, 9999));
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password());
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        userPayload.setUserStatus(1);

        logger.info(">>> Test user created with username: {}", userPayload.getUsername());
    }

    @Test(priority = 1)
    public void testPostUser() {
        logger.info("Sending request to create user: {}", userPayload.getUsername());
        Response res = UserEndpoints2.createUser(userPayload);
        res.then().log().all();
        Assert.assertEquals(res.statusCode(), 200, "User creation failed!");
        logger.info("User creation test passed.");
    }

    @Test(priority = 2, dependsOnMethods = "testPostUser")
    public void testGetUserByName() {
        logger.info("Fetching user by username: {}", userPayload.getUsername());

        Response res = UserEndpoints2.getUser(userPayload.getUsername());
        res.then().log().all();
        Assert.assertEquals(res.statusCode(), 200, "User not found after creation!");
        logger.info("User retrieval test passed.");
    }

    @Test(priority = 3, dependsOnMethods = "testGetUserByName")
    public void testUpdateUser() {
        logger.info("Updating user: {}", userPayload.getUsername());

        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password());
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        Response res = UserEndpoints2.updateUser(userPayload, userPayload.getUsername());
        res.then().log().all();
        Assert.assertEquals(res.statusCode(), 200, "User update failed!");
        logger.info("User update request successful.");

        // Confirm update
        Response res1 = UserEndpoints2.getUser(userPayload.getUsername());
        res1.then().log().all();
        Assert.assertEquals(res1.statusCode(), 200, "Updated user not found!");
        logger.info("User update verified successfully.");
    }

    @Test(priority = 4, dependsOnMethods = "testUpdateUser")
    public void testDeleteUser() {
        logger.info("Deleting user: {}", userPayload.getUsername());
        Response res = UserEndpoints2.deleteUser(userPayload.getUsername());
        res.then().log().all();

        Assert.assertTrue(res.statusCode() == 200 || res.statusCode() == 404,
                "Unexpected status code on delete: " + res.statusCode());
        logger.info("Delete test executed with status code: {}", res.statusCode());
    }
}
