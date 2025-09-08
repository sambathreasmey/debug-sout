package io.github.<you>.debug;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DebugSoutAspect {
    private static final DefaultParameterNameDiscoverer NAMES = new DefaultParameterNameDiscoverer();

    @Around("@annotation(io.github.<you>.debug.DebugSout)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature sig = (MethodSignature) pjp.getSignature();
        var method = sig.getMethod();
        var ann = method.getAnnotation(DebugSout.class);

        String clazz = sig.getDeclaringTypeName();
        String name  = sig.getName();
        String[] params = NAMES.getParameterNames(method);
        Object[] args   = pjp.getArgs();

        System.out.println("---- DEBUG " + clazz + "." + name + " ----");
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                System.out.println("  " + params[i] + " = " + String.valueOf(args[i]));
            }
        }

        long t0 = System.currentTimeMillis();
        Object out = pjp.proceed();
        long t1 = System.currentTimeMillis();

        if (ann.printReturn() && sig.getReturnType() != void.class) {
            System.out.println("  return = " + String.valueOf(out));
        }
        if (ann.printExecutionTime()) {
            System.out.println("  took = " + (t1 - t0) + " ms");
        }
        System.out.println("---- END DEBUG ----\n");
        return out;
    }
}