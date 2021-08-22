package ru.butakov.springdemo.reeper1.context;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SendToTheWallBeanPostProcessor implements BeanPostProcessor {
    Map<String, Class<?>> map = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        var beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(SendToTheWall.class)) {
            map.put(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        var beanClass = map.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(),
                    (o, method, args) -> {
                        System.out.println("INJECTED BEFORE: I have to go to the wall...");
                        var result = method.invoke(bean, args);
                        System.out.println("INJECTED AFTER: I have arrived at the wall...");
                        return result;
                    });
        }
        return bean;
    }
}
