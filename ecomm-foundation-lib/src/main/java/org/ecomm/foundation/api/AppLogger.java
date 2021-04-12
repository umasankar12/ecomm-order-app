package org.ecomm.foundation.api;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AppLogger {

    String value() default "";

}
