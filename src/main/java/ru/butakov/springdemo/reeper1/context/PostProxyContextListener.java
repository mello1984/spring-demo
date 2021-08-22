package ru.butakov.springdemo.reeper1.context;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PostProxyContextListener implements ApplicationListener<ContextRefreshedEvent> {
    ConfigurableListableBeanFactory factory;

    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        var context = event.getApplicationContext();
        var names = context.getBeanDefinitionNames();
        for (String name : names) {
            var beanDefinition = factory.getBeanDefinition(name);
            var originalClassName = beanDefinition.getBeanClassName();
            var originalClass = Class.forName(originalClassName);
            var methods = originalClass.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getAnnotation(PostProxy.class) != null) {
                    Object bean = context.getBean(name);
                    var targetMethod = bean.getClass().getMethod(method.getName(), method.getParameterTypes());
                    System.out.println("POST_PROXY_LISTENER BEFORE");
                    ReflectionUtils.invokeMethod(targetMethod, bean);
                    System.out.println("POST_PROXY_LISTENER AFTER");
                }
            }
        }
    }
}
