package com.luv2code.aopdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.luv2code.aopdemo")
@PropertySource("classpath:mylogger.properties")
public class DemoConfig {

    @Bean
    public MyLoggerConfig2 myLoggerConfig2(@Value("${root.logger.level}") String rootLoggerLevel,
                                           @Value("${printed.logger.level}") String printedLoggerLevel) {

        return new MyLoggerConfig2(rootLoggerLevel, printedLoggerLevel);

    }

}
