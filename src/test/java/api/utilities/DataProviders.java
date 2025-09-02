package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

    /**
     * DataProvider for Users sheet in Excel.
     * Returns all rows except the header as Object[][].
     */
    @DataProvider(name = "userData")
    public static Object[][] getUserData() {
        return ExcelUtils.getTestData("Users"); // Replace with your sheet name if different
    }
    @DataProvider(name = "allUsernames", parallel = false)
    public static Object[][] getAllUsernames() {
        Object[][] data = ExcelUtils.getTestData("Users"); // your sheet name
        Object[][] usernames = new Object[data.length][1]; // only need username column
        for (int i = 0; i < data.length; i++) {
            usernames[i][0] = data[i][1]; // assuming column 1 = username
        }
        return usernames;
    }

    /**
     * Example: DataProvider for Config sheet (key-value pairs)
     */
    @DataProvider(name = "configData")
    public static Object[][] getConfigData() {
        return ExcelUtils.getTestData("Config"); // Returns key-value from Config sheet
    }

    /**
     * Example: DataProvider for Numbers sheet (numeric data as Strings)
     */
    @DataProvider(name = "numberData")
    public static Object[][] getNumberData() {
        return ExcelUtils.getTestData("Numbers"); // Returns all numeric data as Strings
    }
}
