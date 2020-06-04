package com.example.spring.domain.annotation;

import com.example.spring.domain.annotation.scanner.MpkScannerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author: Staro
 * @date: 2020/5/27 17:32
 * @Description:
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Import(MpkScannerRegistrar.class)
@Documented
public @interface MpkScanner {
    String[] basePackage() default {};
}
