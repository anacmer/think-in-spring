package com.anacmer.learn.spring.bean.factory;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * @Author ZhaoJQ
 * @Date 2022/5/23 22:19
 */
public class DefaultUserFactory implements UserFactory, InitializingBean {

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
}
