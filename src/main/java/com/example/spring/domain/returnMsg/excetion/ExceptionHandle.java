package com.example.spring.domain.returnMsg.excetion;

import com.example.spring.domain.returnMsg.ReturnBody;
import com.example.spring.domain.returnMsg.codeMsg.ReturnMsgImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: Staro
 * @date: 2019/3/1317:01
 * @Description:
 */
@Slf4j
@RestControllerAdvice // 控制层通知器，这里用于统一拦截异常，进行响应处理
public class ExceptionHandle {
    /** 接口异常 */
    @ExceptionHandler(value = ServletException.class)
    public ReturnBody servletHandler(ServletException e){
        return new ReturnBody<>(ReturnMsgImpl.APPLICATION_EXCEPTION, e.getMessage());
    }

    /** 请求方法不支持 */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ReturnBody methodNotSupportedHandler(HttpRequestMethodNotSupportedException e){
        return new ReturnBody(HttpStatus.METHOD_NOT_ALLOWED);
    }

    /** 找不到页面 */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ReturnBody notFoundHandler(NoHandlerFoundException e){
        return new ReturnBody(HttpStatus.NOT_FOUND);
    }

    /** 缺少参数 */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ReturnBody missParamHandler(MissingServletRequestParameterException e){
        return new ReturnBody(ReturnMsgImpl.MISSING_PARAMETER);
    }

    /** 参数入参格式错误 */
    @ExceptionHandler(value = NumberFormatException.class)
    public ReturnBody numFormatHandler(NumberFormatException e) {
        return new ReturnBody<>(ReturnMsgImpl.ILLEGAL_PARAMETER, e.getMessage());
    }

    /** 自定义异常 */
    @ExceptionHandler(value = GlobalException.class)
    public ReturnBody globalHandler(HttpServletRequest request, GlobalException exception) {
        return new ReturnBody(exception.getReturnMsg());
    }

    /**
     * 参数校验异常：针对Controller里在请求参数对象里写参数校验标签的异常捕获 <br/>
     * 例：<br/>
     * - @RestController <br/>
     * - public class TestController { <br/>
     * -    @GetMapping("/test") <br/>
     * -    // 参数前加@Valid，使参数校验生效，作用于当前参数对象，参数校验标签写在了TestBean里的成员上 <br/>
     * -    public void test(@Valid TestBean params){} <br/>
     * - }
     */
    @ExceptionHandler(value = BindException.class)
    public ReturnBody validBindHandler(HttpServletRequest request, BindException exception) {
        return new ReturnBody<>(ReturnMsgImpl.ILLEGAL_PARAMETER, exception.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
    }

    /**
     * 参数校验异常：针对Controller里在请求参数前直接写参数校验标签的异常捕获 <br/>
     * 例：<br/>
     * - @Validated       // 使参数校验生效，作用于类<br/>
     * - @RestController <br/>
     * - public class TestController { <br/>
     * -    @GetMapping("/test") <br/>
     * -    public void test(@Min(value = 0, message = "age > 0") @RequestParam(required = false)Integer age){} <br/>
     * - }
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ReturnBody methodArgumentNotValidHandler(HttpServletRequest request, MethodArgumentNotValidException exception) {
//        //按需重新封装需要返回的错误信息
//        List<Map<String , Object>> invalidArguments = new ArrayList<>();
//        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
//        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("message", error.getDefaultMessage());
//            map.put("field", error.getField());
//            invalidArguments.add(map);
//        }
        return new ReturnBody<>(ReturnMsgImpl.ILLEGAL_PARAMETER, exception.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
    }

//    @ExceptionHandler(value = ValidationException.class)
//    public ReturnBody validationHandler(HttpServletRequest request, ValidationException exception) {
//        String error = exception.getLocalizedMessage();
//        String head = error.substring(0, error.indexOf(".")+1);
//        Integer appear = CharUtil.appearNumber(error, head);
//        if (appear == 1)
//            return new ReturnBody<>(ReturnMsgImpl.ILLEGAL_PARAMETER, error.substring(error.indexOf(":") + 1));
//        else
//            return new ReturnBody<>(ReturnMsgImpl.ILLEGAL_PARAMETER, error.substring(error.indexOf(":") + 1, CharUtil.findLowerIndex(error, head, 2) - 2));
//    }
}
