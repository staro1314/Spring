package com.example.spring.domain.returnMsg.codeMsg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

/**
 * @author: Staro
 * @date: 2019/3/1316:42
 * @Description:
 */
public enum ReturnMsgImpl implements ReturnMsg {
    SUCCESS(0, "SUCCESS"),
    APPLICATION_EXCEPTION(1001,"APPLICATION_EXCEPTION"),
    MISSING_PARAMETER(1002,"MISSING_PARAMETER"),
    ILLEGAL_PARAMETER(1003,"ILLEGAL_PARAMETER"),
    SQL_EXCU_ERROR(1004,"SQL_EXCU_ERROR"),
    USER_ADD_FAIL(1005,"USER_ADD_FAIL"),
    USER_DELETE_FAIL(1006,"USER_DELETE_FAIL"),
    USER_UPDATE_FAIL(1007,"USER_UPDATE_FAIL"),
    FILE_FORMAT_NOT_SUPPORT(1008,"FILE_FORMAT_NOT_SUPPORT"),
    FILE_RESOLVE_FAIL(1009,"FILE_RESOLVE_FAIL"),
    FOLDER_CREATE_FAIL(1010,"FOLDER_CREATE_FAIL"),
    FILE_CREATE_FIAL(1011,"FILE_CREATE_FAIL"),
    ;


    private int status;
    private String message;
    private static MessageSource messageSource;

    ReturnMsgImpl(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static void setMessageResult(MessageSource messageSource){
        ReturnMsgImpl.messageSource=messageSource;
    }


    @Component
    public static class InnerMessageResult{

        private final MessageSource messageSource;


        @Autowired
        public InnerMessageResult(MessageSource messageSource) {
            this.messageSource = messageSource;
        }

        @PostConstruct
        public void postConstruct() {
            ReturnMsgImpl.setMessageResult(messageSource);
        }
    }


    @Override
    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return messageSource.getMessage(message,null, Locale.CHINA);
    }
}
