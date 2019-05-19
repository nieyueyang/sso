package com.deyun.sso.aop;

import com.alibaba.fastjson.JSONObject;
import com.deyun.common.enums.ErrorMsgEnum;
import com.deyun.common.exception.GlobalException;
import com.deyun.common.util.HttpUtil;
import com.deyun.common.util.IllegalStrUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Aspect
public class GlobalLogAndParameterHandle {

    private static final Logger logger = LoggerFactory.getLogger(GlobalLogAndParameterHandle.class);
    //TODO 还没有获取登录用户
    private String account;

    @Pointcut("execution(* com.deyun.*.ctrl..*.*(..))")
    public void pointCut(){}

    @Around("pointCut()")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        HttpServletResponse response = sra.getResponse();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String targetIp = HttpUtil.getRemoteHost(request);
        //TODO 还没有获取登录用户
        logger.info("account:{}, targetIp:{}, url:{}, method:{}",account,targetIp,url,method);
        JSONObject jsonObject = checkRequestParam(pjp,response);
        logger.info("输入参数：{}",jsonObject.toJSONString());
        // pjp.proceed的值就是被拦截方法的返回值
        return pjp.proceed();
    }

    /**
     * @Author: nieyy
     * @Description: 处理入参特殊字符和sql注入攻击
     * @Date: 15:37 2019/4/25
     */
    private JSONObject checkRequestParam(ProceedingJoinPoint pjp,HttpServletResponse response) throws GlobalException {
        JSONObject jsonObject = new JSONObject();
        //参数类型
        Class[] parameterTypes = ((MethodSignature)pjp.getSignature()).getMethod().getParameterTypes();
        //参数名
        String[] keys = ((MethodSignature)pjp.getSignature()).getParameterNames();
        //参数值
        Object[] values = pjp.getArgs();

        for (int i = 0; i < parameterTypes.length; i++){
            if(parameterTypes[i] != HttpServletRequest.class &&  parameterTypes[i] != HttpServletResponse.class){
                String value = String.valueOf(values[i]);

                if (!IllegalStrUtil.sqlStrFilter(value)) {
                    logger.info("输入参数存在SQL注入风险！参数为{}:{}",keys[i],values[i]);
                    throw new GlobalException(ErrorMsgEnum.PARAM_ERROR);
                }
                if (!IllegalStrUtil.isIllegalStr(value)) {
                    logger.info("输入参数含有非法字符!参数为{}:{}",keys[i],values[i]);
                    throw new GlobalException(ErrorMsgEnum.PARAM_ERROR);
                }

                jsonObject.put(keys[i],value);
            }
        }
        return jsonObject;
    }

      //异常处理增强
//    @AfterThrowing(pointcut = ("pointCut()"), throwing = "e")
//    public Result exceptionHandle(JoinPoint point, RuntimeException e) {
//        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//        HttpServletRequest request = sra.getRequest();
//        HttpServletResponse response = sra.getResponse();
//        Result result = defaultErrorHandler(e);
//        //HttpUtil.responseWriteJson(response, result);
//        return result;
//
//    }

      //返回值增强处理
//    @AfterReturning(pointcut = ("pointCut()"), returning = "returnValue")
//    public void log(JoinPoint point, Object returnValue) {
//        System.out.println("@AfterReturning：模拟日志记录功能...");
//        System.out.println("@AfterReturning：目标方法为：" +
//                point.getSignature().getDeclaringTypeName() +
//                "." + point.getSignature().getName());
//        System.out.println("@AfterReturning：参数为：" +
//                Arrays.toString(point.getArgs()));
//        System.out.println("@AfterReturning：返回值为：" + returnValue);
//        System.out.println("@AfterReturning：被织入的目标对象为：" + point.getTarget());
//
//    }


    /**
     * 后置返回通知
     * 这里需要注意的是:
     * 如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     * 如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning 限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
     */
//    @AfterReturning(value ="pointCut()",returning="t")
//    public <T> Result returnDataHandle(ProceedingJoinPoint pjp, T t) {
//        Result result = new Result();
//        result.setCode(200);
//        result.setMsg("访问成功");
//        result.setData(t);
//        return result;
//    }



}
