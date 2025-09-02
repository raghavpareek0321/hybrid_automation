package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {
    
    public static Response createUser(User payload) {
        Response res = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
        .when()
                .post(Routes.post_url);  
        return res;
    }
    public static Response getUser( String userName) {
        Response res=given()
        		.pathParam("username",userName)
        		.when()
        		.get(Routes.get_url);
        return res;
    }
    public static Response updateUser(User payload,String userName) {
        Response res = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .pathParam("username",userName)
        .when()
                .put(Routes.put_url);  
        return res;
    }
    public static Response deleteUser( String userName) {
        Response res=given()
        		.pathParam("username",userName)
        		.when()
        		.delete(Routes.delete_url);
        return res;
    }
      
}
