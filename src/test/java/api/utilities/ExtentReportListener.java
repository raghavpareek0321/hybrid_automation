package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import org.testng.ISuite;
import org.testng.ISuiteListener;

/**
 * Proper TestNG listener for Extent Reports.
 * Extends ExtentITestListenerClassAdapter so it is compatible with TestNG.
 */
public class ExtentReportListener extends ExtentITestListenerClassAdapter implements ISuiteListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ISuite suite) {
        // Initialize ExtentReports at suite start
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("PetStore Automation Report");
        spark.config().setReportName("API + UI Test Results");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Project", "PetStore Automation");
        extent.setSystemInfo("Tester", System.getProperty("user.name"));
        extent.setSystemInfo("OS", System.getProperty("os.name"));

        System.out.println("===== Extent Report Initialized =====");
    }

    @Override
    public void onFinish(ISuite suite) {
        if (extent != null) {
            extent.flush();
            System.out.println("===== Extent Report Generated =====");
        }
    }

    public static ExtentReports getExtentReports() {
        return extent;
    }

    public static ExtentTest createTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
        return extentTest;
    }

    public static ExtentTest getTest() {
        return test.get();
    }
}
