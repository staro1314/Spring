package com.example.spring.domain.returnMsg.excetion;


import com.example.spring.domain.returnMsg.codeMsg.ReturnMsg;

/**
 * @author: Staro
 * @date: 2019/3/1316:58
 * @Description:
 */
public class GlobalException extends Exception{
    private ReturnMsg returnMsg;

    public GlobalException(ReturnMsg returnMsg) {
        this.returnMsg = returnMsg;
    }

    public ReturnMsg getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(ReturnMsg returnMsg) {
        this.returnMsg = returnMsg;
    }
}
