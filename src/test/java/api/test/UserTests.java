package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTests {

    @Test(groups = {"smoke"})
    public void testCreateUser() {
        // create user API call
        System.out.println("Running Create User (smoke)");
        Assert.assertEquals(200, 200);
    }

    @Test(groups = {"regression"})
    public void testGetUserByName() {
        // get user by name
        System.out.println("Running Get User By Name (regression)");
        Assert.assertEquals(200, 200, "User not found after creation!");
    }

    @Test(groups = {"regression", "update"})
    public void testUpdateUser() {
        // update user API call
        System.out.println("Running Update User (regression, update)");
        Assert.assertEquals(200, 200, "Updated user not found!");
    }

    @Test(groups = {"smoke", "delete"})
    public void testDeleteUser() {
        // delete user API call
        System.out.println("Running Delete User (smoke, delete)");
        Assert.assertEquals(200, 200);
    }
}
