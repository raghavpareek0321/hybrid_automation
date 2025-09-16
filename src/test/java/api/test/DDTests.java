package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.UserPayload;
import api.utilities.ExcelDataProviders;
import io.restassured.response.Response;

public class DDTests {

    @Test(dataProvider = "UserData", dataProviderClass = ExcelDataProviders.class)
    public void testCreateUserDD(String id, String username, String firstName, String lastName, String email, String password, String phone) {
        UserPayload user = new UserPayload(Integer.parseInt(id), username, firstName, lastName, email, password, phone);
        Response response = UserEndPoints.createUser(user);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(dataProvider = "UserData", dataProviderClass = ExcelDataProviders.class)
    public void testReadUserDD(String id, String username, String firstName, String lastName, String email, String password, String phone) {
        Response response = UserEndPoints.readUser(username); // ✅ use username string
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
