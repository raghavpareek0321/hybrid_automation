package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User; 
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {

    @Test(priority = 1, dataProvider = "userData", dataProviderClass = DataProviders.class)
    public void testPostUser(String id, String username, String firstName, String lastName,
                             String email, String password, String phone, String userStatus) {

        // Create POJO object
        User userPayload = new User();
        userPayload.setId(Long.parseLong(id));
        userPayload.setUsername(username);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(email);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);
        userPayload.setUserStatus(Integer.parseInt(userStatus));

      
        Response res = UserEndpoints.createUser(userPayload);

        res.then();

     
        Assert.assertEquals(res.statusCode(), 200, "User creation failed!");
    }
    @Test(priority = 99, dataProvider = "allUsernames", dataProviderClass = DataProviders.class)
    public void deleteAllUsers(String username) {
        Response res = UserEndpoints.deleteUser(username);
        res.then();

        int statusCode = res.getStatusCode();
        Assert.assertTrue(
            statusCode == 200 || statusCode == 404,
            "Unexpected status while deleting user: " + username + " | Status: " + statusCode
        );
    }

}
