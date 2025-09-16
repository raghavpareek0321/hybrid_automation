package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "UserData")
    public Object[][] getUserData() {
        ExcelUtils excel = new ExcelUtils("src/test/resources/TestData.xlsx", "Users");
        return excel.getTestData("Users");
    }

    @DataProvider(name = "ISAData")
    public Object[][] getISAData() {
        ExcelUtils excel = new ExcelUtils("src/test/resources/TestData.xlsx", "ISA");
        return excel.getTestData("ISA");
    }

    @DataProvider(name = "PaymentData")
    public Object[][] getPaymentData() {
        ExcelUtils excel = new ExcelUtils("src/test/resources/TestData.xlsx", "Payments");
        return excel.getTestData("Payments");
    }

    @DataProvider(name = "OtherData")
    public Object[][] getOtherData() {
        ExcelUtils excel = new ExcelUtils("src/test/resources/TestData.xlsx", "Other");
        return excel.getTestData("Other");
    }
}
