package api.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Retry failed tests up to a maximum count
 * and log retry attempts in ExtentReports.
 */
public class RetryListener implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 2; // retry up to 2 times

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;

            // Log retry info into ExtentReport if available
            try {
                if (ExtentReportManager.getTest() != null) {
                    ExtentReportManager.getTest().info(
                        "Retrying test '" + result.getName() +
                        "' (attempt " + retryCount + " of " + maxRetryCount + ")"
                    );
                }
            } catch (Exception e) {
                System.err.println("ExtentReport logging failed in RetryListener: " + e.getMessage());
            }

            return true; // tell TestNG to retry the test
        }
        return false; // no more retries
    }
}
