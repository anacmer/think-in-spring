package com.anacmer.learn.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author ZhaoJQ
 * @Date 2022/5/23 22:19
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    /**
     * 三种方法初始化 Spring Bean
     * 优先级分别如下
     * 1.@PostConstruct
     * 2.InitializingBean#afterPropertiesSet
     * 3.BeanDefinition#initMethod
     */

    @Override
    public void afterPropertiesSet() {
        System.out.println("afterPropertiesSet(), UserFactory 初始化中...");
    }

    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct注解, UserFactory 初始化中...");
    }


    @Override
    public void initUserFactory() {
        System.out.println("自定义初始化方法 initUserFactory(), UserFactory初始化中...");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy: UserFactory销毁中...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy()方法：UserFactory销毁中...");
    }

    public void doDestroy() {
        System.out.println("自定义销毁方法 doDestroy()：UserFactory销毁中...");
    }
}
