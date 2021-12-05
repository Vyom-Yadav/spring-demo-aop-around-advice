package com.luv2code.aopdemo;

import com.luv2code.aopdemo.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class AroundDemoApp {

    private static final Logger myLogger =
            Logger.getLogger(AroundDemoApp.class.getName());

    public static void main(String[] args) {


        // read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean from spring container
        TrafficFortuneService trafficFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);
        myLogger.info("======>start");
//        myLogger.info(trafficFortuneService.getFortune());
        myLogger.info(trafficFortuneService.getFortune(true));
        myLogger.info("======>end");

        myLogger.info("Finished");
        // close the context
        context.close();
    }

}










