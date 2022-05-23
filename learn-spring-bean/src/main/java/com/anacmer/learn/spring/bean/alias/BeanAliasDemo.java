package com.anacmer.learn.spring.bean.alias;

import com.anacmer.learn.spring.pojo.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 别名示例
 *
 * @Author ZhaoJQ
 * @Date 2022/5/17 23:34
 */
public class BeanAliasDemo {

    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/application-context.xml");

        User user = beanFactory.getBean("user", User.class);
        User userZhaojq = beanFactory.getBean("user-zhaojq", User.class);
        //判断获取的是否为同一个Bean
        System.out.println("判断user和user-zhaojq是否为同一个Bean,结果为：" + (user == userZhaojq));

    }
}
