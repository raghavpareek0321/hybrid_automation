package api.utilities;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/** Tune retries for a specific test/class without changing global props. */
@Retention(RUNTIME)
@Target({METHOD, TYPE})
public @interface Flaky {
    int retries() default -1;          // -1 = use global default
    long delayMs() default -1;         // initial delay before 1st retry, -1 = global
    double backoff() default -1;       // exponential factor (e.g., 2.0), -1 = global
    boolean onlyOnTransient() default true; // retry only transient failures
    boolean jitter() default true;     // add +/-10% randomization to delay
}
