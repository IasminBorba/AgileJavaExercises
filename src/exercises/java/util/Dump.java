package util;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Dump {
    int order() default Integer.MAX_VALUE;

    boolean quote() default false;

    String[] outputMethods() default {"toString"};
}