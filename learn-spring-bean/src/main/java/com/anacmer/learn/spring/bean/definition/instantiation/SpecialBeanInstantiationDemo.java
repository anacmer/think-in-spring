package com.anacmer.learn.spring.bean.definition.instantiation;

import com.anacmer.learn.spring.bean.factory.DefaultUserFactory;
import com.anacmer.learn.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ServiceLoader;

/**
 * @Author ZhaoJQ
 * @Date 2022/5/23 22:12
 */
public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {
        // 配置XML配置文件
        // 启动 Spring 应用上下文
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation.xml");
        // 通过ApplicationContext 来获取 AutowireCapableBeanFactory
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
        ServiceLoader<UserFactory> serviceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);
        displayServiceLoad(serviceLoader);
//        demoServiceLoad();
        DefaultUserFactory userFactory = beanFactory.createBean(DefaultUserFactory.class);
        System.out.println(userFactory.createUser());
    }

    private static void demoServiceLoad() {
        ServiceLoader<UserFactory> userFactories = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        displayServiceLoad(userFactories);
    }

    private static void displayServiceLoad(ServiceLoader<UserFactory> userFactories) {
        for (UserFactory userFactory : userFactories) {
            System.out.println(userFactory.createUser());
        }
    }
}
