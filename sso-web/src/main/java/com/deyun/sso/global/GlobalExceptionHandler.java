package com.deyun.sso.global;

import com.deyun.common.dto.Result;
import com.deyun.common.enums.ErrorMsgEnum;
import com.deyun.common.enums.ErrorUserMsgEnum;
import com.deyun.common.exception.GlobalException;
import com.deyun.common.exception.UserException;
import com.deyun.common.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class GlobalExceptionHandler extends AbstractErrorController {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }


    @PostMapping(value = "/error")
    public void error(HttpServletRequest request, HttpServletResponse response) {
        Result result = new Result();
        Throwable throwable = getCause(request);
        if (throwable != null){
            result = defaultErrorHandler((Exception) throwable);
        }else{
            int httpCode  = response.getStatus();
            if (httpCode == 400){
                result.setCode(ErrorMsgEnum.PARAM_NOT_FOUND.getCode());
                result.setMsg(ErrorMsgEnum.PARAM_NOT_FOUND.getMsg());
            } else if (httpCode == 403){
                result.setCode(ErrorUserMsgEnum.AUTHORIZATION_NOT_FOUND.getCode());
                result.setMsg(ErrorUserMsgEnum.AUTHORIZATION_NOT_FOUND.getMsg());
            } else if (httpCode == 404){
                result.setCode(ErrorMsgEnum.NOT_FOUND.getCode());
                result.setMsg(ErrorMsgEnum.NOT_FOUND.getMsg());
            }else if (httpCode == 405){
                result.setCode(ErrorMsgEnum.NOT_FOUND.getCode());
                result.setMsg(ErrorMsgEnum.NOT_FOUND.getMsg());
            }else if(httpCode == 500){
                result.setCode(ErrorMsgEnum.INTERNAL_SERVER_ERROR.getCode());
                result.setMsg(ErrorMsgEnum.INTERNAL_SERVER_ERROR.getMsg());
            }else{
                result.setCode(ErrorMsgEnum.UnknowErrorMsg.getCode());
                result.setMsg(ErrorMsgEnum.UnknowErrorMsg.getMsg());
            }
        }
        HttpUtil.responseWriteJson(response, result);
    }

    private Result defaultErrorHandler(Exception ex){
        Result result = new Result();
        if (ex instanceof UserException) {
            result.setCode(((UserException) ex).getExceptionCode());
            result.setMsg(((UserException) ex).getExceptionMsg());

        }else if (ex instanceof GlobalException){
            result.setCode(((GlobalException) ex).getGlobalExceptionCode());
            result.setMsg(((GlobalException) ex).getGlobalExceptionMsg());
        }else{
            result.setCode(ErrorMsgEnum.UnknowErrorMsg.getCode());
            result.setMsg(ErrorMsgEnum.UnknowErrorMsg.getMsg());
        }
        return result;
    }

    private Throwable getCause(HttpServletRequest request) {
        Throwable error = (Throwable) request.getAttribute("javax.servlet.error.exception");
        if (error != null) {
            while (error instanceof Exception && error.getCause() != null) {
                error = error.getCause();
            }
        }
        return error;
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}