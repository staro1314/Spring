package com.example.spring.domain.annotation.scanner;

import com.example.spring.domain.annotation.Token;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

/**
 * @author: Staro
 * @date: 2020/5/29 17:14
 * @Description:
 */
public class MpkScannerBeanNameGenerator extends AnnotationBeanNameGenerator {

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        //从自定义注解中拿name
        String name = getNameByServiceFindAnnotation(definition, registry);
        if (name != null && !"".equals(name)) {
            String c = String.valueOf(name.charAt(0)).toLowerCase();
            StringBuilder sb=new StringBuilder(name);
            StringBuilder replace = sb.replace(0, 1, c);
            return replace.toString();
        }
        name=super.generateBeanName(definition, registry);
        //走父类的方法
        return name;
    }

    private String getNameByServiceFindAnnotation(BeanDefinition definition, BeanDefinitionRegistry registry) {
        String beanClassName = definition.getBeanClassName();
        try {
            Class<?> aClass = Class.forName(beanClassName);
            String alia = null;
            Token annotation = aClass.getAnnotation(Token.class);
            Class<?>[] aClassInterfaces = aClass.getInterfaces();
            if (annotation == null) {
                return null;
            }
            if (aClassInterfaces.length != 0)
                alia = aClassInterfaces[0].getSimpleName();
            //获取到注解name的值并返回
            if (StringUtils.isEmpty(annotation.name()))
                return alia;
            return annotation.name();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
