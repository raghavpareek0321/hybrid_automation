package api.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

/**
 * Core retry logic for TestNG test methods.
 * This decides *when* a failed test should be retried.
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    private static final int MAX_RETRIES =
            Integer.getInteger("retry.max", 1); // -Dretry.max=2
    private static final long DELAY_MS =
            Long.getLong("retry.delayMs", 800L);
    private static final double BACKOFF =
            Double.parseDouble(System.getProperty("retry.backoff", "2.0"));
    private static final boolean ONLY_ON_TRANSIENT =
            Boolean.parseBoolean(System.getProperty("retry.onlyOnTransient", "true"));
    private static final boolean JITTER =
            Boolean.parseBoolean(System.getProperty("retry.jitter", "true"));

    private int attempt = 0;
    private static final Random RNG = new Random();

    @Override
    public boolean retry(ITestResult result) {
        if (attempt >= MAX_RETRIES) return false;

        boolean shouldRetry = !ONLY_ON_TRANSIENT || isTransient(result.getThrowable());
        if (!shouldRetry) return false;

        attempt++;

        long delay = computeDelay(DELAY_MS, BACKOFF, attempt, JITTER);
        System.out.printf("Retrying %s (attempt %d/%d) after %d ms%n",
                result.getMethod().getMethodName(), attempt, MAX_RETRIES, delay);

        sleep(delay);
        return true;
    }

    /** Check if the failure looks like a transient issue */
    private boolean isTransient(Throwable t) {
        if (t == null) return false;
        if (matchesAnyCause(t, Set.of(SocketTimeoutException.class, ConnectException.class, SocketException.class))) {
            return true;
        }
        String msg = Objects.toString(t.getMessage(), "");
        return msg.matches(".*\\b5\\d\\d\\b.*") || msg.toLowerCase().contains("gateway timeout");
    }

    private boolean matchesAnyCause(Throwable t, Set<Class<?>> types) {
        Throwable cur = t;
        while (cur != null) {
            for (Class<?> c : types) if (c.isInstance(cur)) return true;
            cur = cur.getCause();
        }
        return false;
    }

    private long computeDelay(long base, double backoff, int attemptIndex, boolean jitter) {
        long delay = (long) (base * Math.pow(backoff, attemptIndex - 1));
        if (jitter) {
            long jitterDelta = Math.round(delay * 0.2 * (RNG.nextDouble() - 0.5)); // +/-10%
            delay = Math.max(0, delay + jitterDelta);
        }
        return delay;
    }

    private void sleep(long ms) {
        try { Thread.sleep(ms); }
        catch (InterruptedException ie) { Thread.currentThread().interrupt(); }
    }
}
