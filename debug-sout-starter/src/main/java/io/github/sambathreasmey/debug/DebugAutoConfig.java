package io.github.<you>.debug;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class DebugAutoConfig {
    @Bean DebugSoutAspect debugSoutAspect() { return new DebugSoutAspect(); }
}