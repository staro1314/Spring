package com.example.spring.domain.annotation.scanner;

import com.example.spring.domain.annotation.Token;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

/**
 * @author: Staro
 * @date: 2020/5/29 17:08
 * @Description:
 */
public class FindMpkServiceClassPathScanHandle extends ClassPathBeanDefinitionScanner {


    public FindMpkServiceClassPathScanHandle(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        super(registry, useDefaultFilters);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {

        addIncludeFilter(new AnnotationTypeFilter(Token.class));
        //调用spring的扫描
        return super.doScan(basePackages);
    }
}
