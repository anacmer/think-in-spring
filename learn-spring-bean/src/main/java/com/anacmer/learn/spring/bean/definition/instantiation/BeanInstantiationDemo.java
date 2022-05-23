package com.anacmer.learn.spring.bean.definition.instantiation;

import com.anacmer.learn.spring.pojo.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author ZhaoJQ
 * @Date 2022/5/23 22:12
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation.xml");
        User user = beanFactory.getBean(User.class);
        System.out.println(user);
    }
}
