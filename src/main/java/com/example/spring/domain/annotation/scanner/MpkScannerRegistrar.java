package com.example.spring.domain.annotation.scanner;

import com.example.spring.domain.annotation.MpkScanner;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;

/**
 * @author: Staro
 * @date: 2020/5/29 16:49
 * @Description:
 */
public class MpkScannerRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {
    private ResourceLoader resourceLoader;

    public MpkScannerRegistrar() {
    }

    /** @deprecated */
    @Deprecated
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader=resourceLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        //获取所有注解的属性和值
        AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(MpkScanner.class.getName()));
        //获取到basePackage的值
        assert annoAttrs != null;
        String[] basePackages = annoAttrs.getStringArray("basePackage");
        //如果没有设置basePackage 扫描路径,就扫描对应包下面的值
        if(basePackages.length == 0){
            basePackages = new String[]{((StandardAnnotationMetadata) annotationMetadata).getIntrospectedClass().getPackage().getName()};
        }

        //自定义的包扫描器
        FindMpkServiceClassPathScanHandle scanHandle = new  FindMpkServiceClassPathScanHandle(beanDefinitionRegistry,false);

        if(resourceLoader != null){
            scanHandle.setResourceLoader(resourceLoader);
        }
        //这里实现的是根据名称来注入
        scanHandle.setBeanNameGenerator(new MpkScannerBeanNameGenerator());
        //扫描指定路径下的接口
        scanHandle.doScan(basePackages);

    }

}
