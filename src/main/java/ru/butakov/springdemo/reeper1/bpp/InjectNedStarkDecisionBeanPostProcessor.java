package ru.butakov.springdemo.reeper1.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import ru.butakov.springdemo.reeper1.JohnSnowImpl;

import java.lang.reflect.Field;

@Component
public class InjectNedStarkDecisionBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        var fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            var annotation = field.getAnnotation(InjectNedStarkDecision.class);
            if (annotation != null) {
                if (bean instanceof JohnSnowImpl) {
                    field.setAccessible(true);
                    System.out.println("BPP.beforeInitialization BEFORE: " + ReflectionUtils.getField(field, bean));
                    ReflectionUtils.setField(field, bean, annotation.decision());
                    System.out.println("BPP.beforeInitialization AFTER: " + ReflectionUtils.getField(field, bean));
                }
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        var fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            var annotation = field.getAnnotation(InjectNedStarkDecision.class);
            if (annotation != null) {
                if (bean instanceof JohnSnowImpl) {
                    field.setAccessible(true);
                    System.out.println("BPP.afterInitialization BEFORE: " + ReflectionUtils.getField(field, bean));
                    ReflectionUtils.setField(field, bean, annotation.decision());
                    System.out.println("BPP.afterInitialization AFTER: " + ReflectionUtils.getField(field, bean));
                }
            }
        }
        return bean;
    }
}
