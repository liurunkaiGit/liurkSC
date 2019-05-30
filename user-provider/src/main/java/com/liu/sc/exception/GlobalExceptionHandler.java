package com.liu.sc.exception;

import com.liu.sc.utils.Response;
import com.liu.sc.utils.enumUtils.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 用来处理bean validation异常
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Response resolveConstraintViolationException(ConstraintViolationException e){
        Response response = new Response();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        if(!CollectionUtils.isEmpty(constraintViolations)){
            StringBuilder msgBuilder = new StringBuilder();
            for(ConstraintViolation constraintViolation :constraintViolations){
                msgBuilder.append(constraintViolation.getMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if(errorMessage.length()>1){
                errorMessage = errorMessage.substring(0,errorMessage.length()-1);
            }
            response.setMessage(errorMessage);
        }
        return response;
    }

    /**
     * 参数校验
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response resolveMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("请求参数校验失败", e);
        BindingResult result = e.getBindingResult();
        List<ObjectError> errors = result.getAllErrors();
        String firstError = errors.isEmpty() ? null : errors.iterator().next().getDefaultMessage();
        String errorMessage = StringUtils.isEmpty(firstError) ? ResponseCode.VALID_FAIL_STAUTS.getMessage() : firstError;
        return Response.fail(ResponseCode.VALID_FAIL_STAUTS.getStatus(),errorMessage);
    }

    /**
     * 运行时异常
     */
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public Response RuntimeExceptionHandler(RuntimeException e){
        if(e instanceof RuntimeException){
            return Response.fail(ResponseCode.FAIL.getStatus(),e.getMessage());
        }
        return Response.fail(ResponseCode.FAIL.getStatus(),e.getMessage());
    }

}
