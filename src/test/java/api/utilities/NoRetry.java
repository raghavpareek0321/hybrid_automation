package api.utilities;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/** Put on a class or method to completely disable test-level retries. */
@Retention(RUNTIME)
@Target({METHOD, TYPE})
public @interface NoRetry {}
