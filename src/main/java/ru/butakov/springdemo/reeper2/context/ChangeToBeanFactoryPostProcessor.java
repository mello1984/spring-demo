package ru.butakov.springdemo.reeper2.context;

import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class ChangeToBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @SneakyThrows
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        var names = beanFactory.getBeanDefinitionNames();
        for (String name : names) {
            var beanDefinition = beanFactory.getBeanDefinition(name);
            var className = beanDefinition.getBeanClassName();
            var beanClass = Class.forName(className);
            var annotation = beanClass.getAnnotation(ChangeTo.class);
            if (annotation != null) {
                beanDefinition.setBeanClassName(annotation.to().getName());
            }
        }
    }
}
