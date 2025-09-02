package api.endpoints;
/*
 Base URL: https://petstore.swagger.io/v2
 Create  - POST   https://petstore.swagger.io/v2/user
 Read    - GET    https://petstore.swagger.io/v2/user/{username}
 Update  - PUT    https://petstore.swagger.io/v2/user/{username}
 Delete  - DELETE https://petstore.swagger.io/v2/user/{username}
 */
public class Routes {
    public static String base_url = "https://petstore.swagger.io/v2";

    public static String post_url   = base_url + "/user";
    public static String get_url    = base_url + "/user/{username}";
    public static String put_url    = base_url + "/user/{username}";
    public static String delete_url = base_url + "/user/{username}";
}
