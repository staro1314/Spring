package com.example.spring.domain.service.serviceImpl;

import com.example.spring.domain.annotation.Token;
import com.example.spring.domain.service.service.AnnotationService;

/**
 * @author: Staro
 * @date: 2020/5/29 17:31
 * @Description:
 */
@Token
public class AnnotationServiceImpl implements AnnotationService {

    public String getToken() {
        return "123";
    }
}
