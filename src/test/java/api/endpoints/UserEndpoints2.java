package api.endpoints;

import api.payload.UserPayload;
import io.restassured.response.Response;

/**
 * Shim class: forwards calls to UserEndPoints
 * This way, existing tests using UserEndPoints2 will still compile.
 */
public class UserEndPoints2 {

    public static Response createUser(UserPayload payload) {
        return UserEndPoints.createUser(payload);
    }

    public static Response getUser(String username) {
        return UserEndPoints.getUser(username);
    }

    public static Response readUser(String username) {
        return UserEndPoints.readUser(username);
    }

    public static Response updateUser(String username, UserPayload payload) {
        return UserEndPoints.updateUser(username, payload);
    }

    public static Response deleteUser(String username) {
        return UserEndPoints.deleteUser(username);
    }
}
