package cc.realtec.real.xxljob.executor.service;

import cc.realtec.real.xxljob.common.annotation.XxlJobInfoRegistry;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Component
public class XxlJobInfoAnnotationScanner {
    private final ApplicationContext applicationContext;

    public XxlJobInfoAnnotationScanner(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public List<Method> findMethodsAnnotatedWithXxlJobRegistry() {
        List<Method> annotatedMethods = new ArrayList<>();

        // Iterate through all Spring beans
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            Object bean = applicationContext.getBean(beanName);
            Class<?> beanClass = bean.getClass();

            // Look through all methods in the bean class
            ReflectionUtils.doWithMethods(beanClass, method -> {
                if (method.isAnnotationPresent(XxlJobInfoRegistry.class)
                        && method.isAnnotationPresent(XxlJob.class)) {
                    annotatedMethods.add(method);
                }
            });
        }

        return annotatedMethods;
    }
}
