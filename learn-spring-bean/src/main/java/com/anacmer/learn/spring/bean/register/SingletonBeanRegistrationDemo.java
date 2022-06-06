package com.anacmer.learn.spring.bean.register;

import com.anacmer.learn.spring.bean.factory.DefaultUserFactory;
import com.anacmer.learn.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 外部单体 Bean 注册 Demo
 *
 * @Author ZhaoJQ
 * @Date 2022/6/6 21:54
 */
public class SingletonBeanRegistrationDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext()) {
            applicationContext.register(SingletonBeanRegistrationDemo.class);
            // 创建一个外部对象
            UserFactory userFactory = new DefaultUserFactory();
            ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();

            beanFactory.registerSingleton("userFactory", userFactory);
            // 启用 Spring 应用上下文
            applicationContext.refresh();
            System.out.println("Spring 应用上下文已启动...");

            UserFactory userFactoryByLookUp = beanFactory.getBean("userFactory", UserFactory.class);
            System.out.println("userFactory == userFactoryByLookUp:" + (userFactoryByLookUp == userFactory));
            applicationContext.close();
            System.out.println("Spring 应用上下文已关闭...");
        }
    }
}
