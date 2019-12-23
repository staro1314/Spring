package com.example.spring.domain.returnMsg.codeMsg;

/**
 * @author: Staro
 * @date: 2019/3/1316:40
 * @Description:
 */
public interface ReturnMsg {
    void setStatus(int status);

    int getStatus();

    void setMessage(String message);

    String getMessage();
}
