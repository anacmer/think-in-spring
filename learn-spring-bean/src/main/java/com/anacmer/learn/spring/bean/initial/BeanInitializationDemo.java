package com.anacmer.learn.spring.bean.initial;

import com.anacmer.learn.spring.bean.factory.DefaultUserFactory;
import com.anacmer.learn.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

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
            System.out.println("Spring 应用上下文已启动...");
            // 非延迟初始化,在 Spring 应用上下文启动完成时,被初始化
            UserFactory userFactory = applicationContext.getBean(UserFactory.class);
            System.out.println(userFactory.createUser());
            applicationContext.close();
            System.out.println("Spring 应用上下文已关闭...");
        }
    }

    /**
     * 从 Spring 5.1 版本之后该方法 提炼到了 BeanDefinition 中
     * 该方法映射了 @Bean 中 initMethod
     * {@link org.springframework.beans.factory.support.AbstractBeanDefinition#setInitMethodName}
     *
     * @return UserFactory
     */
    @Lazy// 不推荐: @Lazy(value = false) 如果不需要延迟初始化,可以直接不加@Lazy注解
    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
