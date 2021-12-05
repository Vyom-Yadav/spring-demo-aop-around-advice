package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLoggerConfig2 {

    public MyLoggerConfig2(String rootLoggerLevel, String printedLoggerLevel) {

        // parse levels
        Level rootLevel = Level.parse(rootLoggerLevel);
        Level printedLevel = Level.parse(printedLoggerLevel);

        // get logger for app context

        // get parent logger
        Logger loggerParent = Logger.getLogger(AnnotationConfigApplicationContext.class.getName());

        // set root logging level
        loggerParent.setLevel(rootLevel);

        // set up console handler
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(printedLevel);
        consoleHandler.setFormatter(new SimpleFormatter());

        // add handler to the logger
        loggerParent.addHandler(consoleHandler);
    }

}
