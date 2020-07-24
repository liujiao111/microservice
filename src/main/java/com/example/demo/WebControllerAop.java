package com.example.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * SpringBoot Aop 切面编程
 * 在业务处理过程中，日志的记录、数据源的切换、事务的处理都可以用AOP的方式来实现
 * 而跟SpringBoot的整合，让我们开发AOP又进一步简化，使面向对象能够更加方便地发挥所长
 */
@Aspect
@Component
public class WebControllerAop {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());


    /**

     * 定义一个切入点.

     * 解释下：

     *

     * ~ 第一个 * 代表任意修饰符及任意返回值.

     * ~ 第二个 * 任意包名

     * ~ 第三个 * 代表任意方法.

     * ~ 第四个 * 定义在web包或者子包

     * ~ 第五个 * 任意方法

     * ~ .. 匹配任意数量的参数.

     */
    @Pointcut("execution(public * com.example.demo..*.*(..))")
    public void webLog() {
    }

    /**
     * 添加前置通知要执行的内容
     * @param joinPoint
     */

    @Before("webLog()")
    public void doBeforeAdvice(JoinPoint joinPoint) {
        logger.info("前置通知。。。。。");
    }


    /**
     * 后置通知
     * @param joinpoint
     */
    @AfterReturning("webLog()")
    public void doAfterReturning(JoinPoint joinpoint) {
        logger.info("后置通知。。。。。");
    }

    /**
     * 发生异常通知
     * @param joinPoint
     * @param throwable
     */
    @AfterThrowing(value = "webLog()", throwing = "throwable") //throwing的值要和下面的throwing名字一致
    public void doAfterThrowingAdvice(JoinPoint joinPoint, Throwable throwable) {
        logger.info(joinPoint.getSignature().getName()); //目标方法
        if(throwable instanceof NullPointerException) {
            logger.info("发生了空指针异常");
        } else {
            logger.info("发生了其他异常");
        }

    }

    /**
     * 后置最终通知，不论是正常返回还是异常退出都会执行
     * @param joinPoint
     */
    @After("webLog()")
    public void doAfterAdvice(JoinPoint joinPoint) {
        logger.info("后置通知最终执行啦");
    }


    /**
     * 环绕通知
     * 环绕通知可以在方法调用前后完成自定义的行为。它也会选择是否继续执行连接点，或直
     * 接返回它自己的返回 或抛 异常来结束执行
     * @param proceedingJoinPoint
     * @return
     */
    @Around("execution(* com.example.demo..*.*(..))")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        logger.info("环绕通知的目标方法名：" + proceedingJoinPoint.getSignature().getName());
        try {
            Object proceed = proceedingJoinPoint.proceed();
            logger.info("环绕通知目标方法执行完成......");
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

}
