package com.anacmer.learn.spring.dependency.lookup;

import com.anacmer.learn.spring.pojo.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 类型安全 依赖查找 demo
 *
 * @Author ZhaoJQ
 * @Date 2022/6/7 23:10
 */
public class TypeSafetyDependencyLookupDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext()) {
            applicationContext.register(TypeSafetyDependencyLookupDemo.class);
            // 启动应用上下文
            applicationContext.refresh();

            // 演示 BeanFactoryGetBean#getBean 方法的安全性 :不安全
            displayBeanFactoryGetBean(applicationContext);
            // 演示 ObjectFactory#getObject 方法的安全性 :不安全
            displayObjectFactoryGetObject(applicationContext);
            // 演示 ObjectProvider#getIfAvailable 方法的安全性 :安全
            displayObjectProviderGetIfAvailable(applicationContext);
            // 演示 ListableBeanFactory#getBeansOfType 方法的安全性 :安全
            displayListableBeanFactory(applicationContext);
            // 演示 ObjectProvider stream 操作的安全性 :安全
            displayObjectProviderStreamOps(applicationContext);
        }
    }

    private static void displayObjectProviderStreamOps(BeanFactory beanFactory) {
        ObjectProvider<User> objectProvider = beanFactory.getBeanProvider(User.class);
        printBeansException("displayObjectProviderStreamOps", () -> objectProvider.stream().forEach(System.out::println));

    }

    private static void displayListableBeanFactory(ListableBeanFactory listableBeanFactory) {
        printBeansException("displayListableBeanFactory", () -> listableBeanFactory.getBeansOfType(User.class));
    }

    private static void displayObjectProviderGetIfAvailable(BeanFactory beanFactory) {
        ObjectProvider<User> objectProvider = beanFactory.getBeanProvider(User.class);
        printBeansException("displayObjectProviderIfAvailableGetObject", objectProvider::getIfAvailable);
    }

    private static void displayObjectFactoryGetObject(BeanFactory beanFactory) {
        // ObjectProvider is ObjectFactory
        ObjectFactory<User> objectFactory = beanFactory.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetObject", objectFactory::getObject);
    }

    public static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printBeansException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
    }

    private static void printBeansException(String source, Runnable runnable) {
        System.err.println("======================");
        System.err.println("source form :" + source);
        try {
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }

    private static void printBeansException(Runnable runnable) {
        try {
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
