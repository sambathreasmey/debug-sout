package io.github.sambathreasmey.debug;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DebugSout {
    boolean printReturn() default true;
    boolean printExecutionTime() default true;
}