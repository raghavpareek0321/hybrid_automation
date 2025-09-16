package api.utilities;

import org.testng.annotations.DataProvider;

public class ExcelDataProviders {

    private static final String FILE_PATH = "src/test/resources/TestData.xlsx";

    @DataProvider(name = "UserData")
    public Object[][] getUserData() {
        ExcelUtils excel = new ExcelUtils(FILE_PATH, "Users");
        return excel.getTestData("Users");
    }

    @DataProvider(name = "LoginData")
    public Object[][] getLoginData() {
        ExcelUtils excel = new ExcelUtils(FILE_PATH, "Login");
        return excel.getTestData("Login");
    }

    @DataProvider(name = "PaymentData")
    public Object[][] getPaymentData() {
        ExcelUtils excel = new ExcelUtils(FILE_PATH, "Payments");
        return excel.getTestData("Payments");
    }
}
