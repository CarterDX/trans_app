package net.trans.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.trans.common.BaseResult;
import net.trans.common.exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  @Auther Cx
 *  @Date 2021-02-03
 *  @Version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * The logger.
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResult defaultExceptionHandler(HttpServletRequest req , Exception e) {
        logger.error("错误详情："+e.getMessage());
        return BaseResult.buildTipsErrorResult(e.getMessage());
    }

    @ExceptionHandler(MyException.class)
    public BaseResult myCustomizeException(MyException ex){
        logger.error("错误详情：" + ex.getMessage());
        return BaseResult.buildTipsErrorResult(ex.getMessage());
    }

    /**
     * 捕获 MethodArgumentNotValidException 异常（valid 验证的异常）
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResult handleMethodArgumentNotValidException(HttpServletRequest req,MethodArgumentNotValidException e) throws JsonProcessingException {

        logger.error("错误详情：" + e.getMessage());
        Map<String, String> errorMsg = new HashMap<String, String>();

        List<FieldError> fieldsList = e.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldsList) {
            errorMsg.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return BaseResult.buildErrorResult(errorMsg);
    }

}
