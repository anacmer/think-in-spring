package com.anacmer.learn.spring.bean.initial;

import com.anacmer.learn.spring.bean.factory.DefaultUserFactory;
import com.anacmer.learn.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean Initialization Demo
 *
 * @Author ZhaoJQ
 * @Date 2022/5/23 23:02
 */
@Configuration // 表示这是一个配置 bean
public class BeanInitializationDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext()) {
            applicationContext.register(BeanInitializationDemo.class);
            // 启用 Spring 应用上下文
            applicationContext.refresh();

            UserFactory userFactory = applicationContext.getBean(UserFactory.class);
            System.out.println(userFactory.createUser());
        }
    }

    /**
     * 从 Spring 5.1 版本之后该方法 提炼到了 BeanDefinition 中
     * 该方法映射了 @Bean 中 initMethod
     * {@link org.springframework.beans.factory.support.AbstractBeanDefinition#setInitMethodName}
     *
     * @return UserFactory
     */
    @Bean(initMethod = "initUserFactory")
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
