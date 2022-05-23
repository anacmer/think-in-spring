package com.anacmer.learn.spring.bean.definition.annotation;

import com.anacmer.learn.spring.pojo.User;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Author ZhaoJQ
 * @Date 2022/5/18 23:04
 */
@Import(AnnotationDefinitionBeanDemo.Config.class)// 3.通过 @Import 的方式
public class AnnotationDefinitionBeanDemo {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext()) {
            // 注册 Configuration 类
            applicationContext.register(AnnotationDefinitionBeanDemo.class);
            // 启用应用上下文
            applicationContext.refresh();
            /**
             * {@link GenericApplicationContext} 实现了{@link BeanDefinitionRegistry}
             * 所以这里可以直接将 applicationContext 传进去
             */
            // 非命名方式
            registerUserBeanDefinition(applicationContext);
            // 指定命名方式
            registerUserBeanDefinition(applicationContext, "user-z");

            // 按照类型查找 Bean
            System.out.println("Config Bean:" + applicationContext.getBeansOfType(Config.class));
            System.out.println("User Bean：" + applicationContext.getBeansOfType(User.class));
        }
    }

    /**
     * 通过 Java API 的方式注册 User Bean
     *
     * @param registry Bean 注册器
     * @param beanName Bean 名称
     */
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition();
        beanDefinitionBuilder
                .addPropertyValue("userId", "user-api")
                .addPropertyValue("name", "zhaojq");

        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // 需要指定 Bean 的类型,不然无法生成默认的 beanName
        beanDefinition.setBeanClass(User.class);
        if (StringUtils.hasText(beanName)) {
            // 通过指定命名的方式注册 Bean
            registry.registerBeanDefinition(beanName, beanDefinition);
        } else {
            // 通过 Spring 默认命名的方式注册 Bean
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry);
        }

    }


    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }


    /**
     * 通过配置类的方式进行 将Bean添加到容器中
     */
    @Component// 2.通过 @Component 的方式
    public static class Config {

        @Bean// 1.通过 @Bean 的方式
        public User user() {
            User user = new User();
            user.setUserId("user-@Bean");
            user.setName("zhaojq");
            return user;
        }

    }
}
