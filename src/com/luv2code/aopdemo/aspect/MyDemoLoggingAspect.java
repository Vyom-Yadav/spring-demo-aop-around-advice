package com.luv2code.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    private final Logger myLogger = Logger.getLogger(getClass().getName());

    @Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // A note on proceedingJoinPoint - It acts as a joinPoint only but has an additional proceed method to trigger the next advice/method.

        // print out the method we are advising on
        String method = proceedingJoinPoint.getSignature().toShortString();
        myLogger.info("=====> Executing around advice on " + method);

        long begin = System.currentTimeMillis();

        // execute the method
        Object result;

        try {
            result = proceedingJoinPoint.proceed();
            return result;
        } catch (Exception e) {
            myLogger.severe(e.getMessage());
            throw e;
        }

//        long end = System.currentTimeMillis();
//
//        // display time.
//        myLogger.info("Time taken: " + (end - begin)/1000 + "seconds");
    }

    // add a new advice for @AfterReturning on the findAccounts method

    @AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(
            JoinPoint theJoinPoint, List<Account> result) {
        // joinPoint contains information about the method being executed.

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        myLogger.info("\n=====>>> Executing @AfterReturning on method: " + method);

        // print out the results of the method call
        myLogger.info("\n=====>>> result is: " + result);

        // let's post-process the data ... let's modify it :-)

        // convert the account names to uppercase
        convertAccountNamesToUpperCase(result);

        myLogger.info("\n=====>>> result is: " + result);

    }

    private void convertAccountNamesToUpperCase(List<Account> result) {

        // loop through accounts

        for (Account tempAccount : result) {

            // get uppercase version of name
            String theUpperName = tempAccount.getName().toUpperCase();

            // update the name on the account
            tempAccount.setName(theUpperName);
        }

    }

    @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {

        myLogger.info("\n=====>>> Executing @Before advice on method");

        // display the method signature
        MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();

        myLogger.info("Method: " + methodSig);

        // display method arguments

        // get args
        Object[] args = theJoinPoint.getArgs();

        // loop thru args
        for (Object tempArg : args) {
            myLogger.info(tempArg.toString());

            if (tempArg instanceof Account) {

                // downcast and print Account specific stuff
                Account theAccount = (Account) tempArg;

                myLogger.info("account name: " + theAccount.getName());
                myLogger.info("account level: " + theAccount.getLevel());

            }
        }

    }

}










