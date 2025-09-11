package api.utilities;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Ensures every test method has RetryAnalyzer attached,
 * unless explicitly opted-out with @NoRetry.
 * Works on both TestNG 7.9.0 and 7.11.0+ (via reflection).
 */
public class RetryListener implements IAnnotationTransformer {

    @Override
    @SuppressWarnings({"rawtypes"})
    public void transform(ITestAnnotation ann, Class testClass, Constructor testCtor, Method testMethod) {
        if (testMethod == null) return;

        // Skip if @NoRetry present
        if (testMethod.isAnnotationPresent(NoRetry.class) ||
            (testClass != null && testClass.isAnnotationPresent(NoRetry.class))) {
            return;
        }

        // Attach RetryAnalyzer if not already attached
        if (!hasRetryAnalyzer(ann)) {
            setRetryAnalyzer(ann, RetryAnalyzer.class);
        }
    }

    /** Reflection helper: does this annotation already have a retry analyzer? */
    private boolean hasRetryAnalyzer(ITestAnnotation ann) {
        try {
            // Old API (TestNG <= 7.9.0)
            Method getter = ann.getClass().getMethod("getRetryAnalyzer");
            return getter.invoke(ann) != null;
        } catch (NoSuchMethodException e1) {
            try {
                // New API (TestNG >= 7.10.0)
                Method getter = ann.getClass().getMethod("getRetryAnalyzerClass");
                return getter.invoke(ann) != null;
            } catch (Exception e2) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /** Reflection helper: set RetryAnalyzer in either old or new API */
    private void setRetryAnalyzer(ITestAnnotation ann, Class<?> clazz) {
        try {
            // Old API
            Method setter = ann.getClass().getMethod("setRetryAnalyzer", Class.class);
            setter.invoke(ann, clazz);
        } catch (NoSuchMethodException e1) {
            try {
                // New API
                Method setter = ann.getClass().getMethod("setRetryAnalyzerClass", Class.class);
                setter.invoke(ann, clazz);
            } catch (Exception e2) {
                throw new RuntimeException("RetryListener: Unable to set RetryAnalyzer", e2);
            }
        } catch (Exception e) {
            throw new RuntimeException("RetryListener: Unable to set RetryAnalyzer", e);
        }
    }
}
