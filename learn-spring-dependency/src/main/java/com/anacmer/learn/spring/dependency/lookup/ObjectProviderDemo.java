package com.anacmer.learn.spring.dependency.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 通过 {@link ObjectProvider} 进行依赖查找
 *
 * @Author ZhaoJQ
 * @Date 2022/6/6 22:21
 */
// @Configuration 是非必须的注解
public class ObjectProviderDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext()) {
            applicationContext.register(ObjectProviderDemo.class);
            // 启动应用上下文
            applicationContext.refresh();

            lookUpBeanByObjectProvider(applicationContext);
        }
    }

    @Bean
    // 不指定 Bean 名称的话，默认 Bean 名称就是方法名
    public String helloWorld() {
        return "Hello World";
    }

    private static void lookUpBeanByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(beanProvider.getObject());
    }
}
