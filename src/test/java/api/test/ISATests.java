package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import api.utilities.ExcelDataProviders;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ISATests {

    @Test(dataProvider = "ISAData", dataProviderClass = api.utilities.ExcelDataProviders.class, groups = {"isa"})
    public void testISAEndpoints(String endpoint, String payload, int expectedStatus) {
        Response response = RestAssured
                .given()
                .baseUri("https://your-base-url-here") // replace with ConfigManager.get("base.url") later
                .body(payload)
                .when()
                .post(endpoint) // or GET/PUT depending on testdata
                .then()
                .extract().response();

        System.out.println("Endpoint: " + endpoint + " | Status: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), expectedStatus, "Status code mismatch!");
    }
}
