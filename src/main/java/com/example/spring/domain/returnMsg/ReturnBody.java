package com.example.spring.domain.returnMsg;

import com.example.spring.domain.returnMsg.codeMsg.ReturnMsg;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author: Staro
 * @date: 2019/3/1316:45
 * @Description:
 */
public class ReturnBody<D> implements Serializable {

    private int status;
    private String message;
    private D data;

    public ReturnBody(ReturnMsg returnMsg) {
        this.status = returnMsg.getStatus();
        this.message = returnMsg.getMessage();
    }

    public ReturnBody(ReturnMsg returnMsg, D data) {
        this.status = returnMsg.getStatus();
        this.message =  returnMsg.getMessage();
        this.data = data;
    }

    public ReturnBody(HttpStatus methodNotAllowed) {
        this.status=methodNotAllowed.value();
        this.message=  methodNotAllowed.getReasonPhrase();
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }
}
