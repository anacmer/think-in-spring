package com.anacmer.learn.spring.dependency.lookup;

import com.anacmer.learn.spring.pojo.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 通过 {@link ObjectProvider} 进行依赖查找
 *
 * @Author ZhaoJQ
 * @Date 2022/6/6 22:21
 */
// @Configuration 是非必须的注解
public class ObjectProviderDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext()) {
            applicationContext.register(ObjectProviderDemo.class);
            // 启动应用上下文
            applicationContext.refresh();

            lookUpBeanByObjectProvider(applicationContext);
            lookUpBeanIfAvailable(applicationContext);
            lookUpBeanByStreamOps(applicationContext);
        }
    }

    private static void lookUpBeanByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        // ObjectProvider 实现了 Iterable 接口，可以直接 foreach
//        for (String str : objectProvider) {
//            System.out.println(str);
//        }
        objectProvider.stream().forEach(System.out::println);
    }

    private static void lookUpBeanIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        User user = objectProvider.getIfAvailable(User::createUer);
        System.out.println("当前 User 对象 ：" + user);
    }

    @Bean
    @Primary
    // 不指定 Bean 名称的话，默认 Bean 名称就是方法名
    public String helloWorld() {
        return "Hello World";
    }

    @Bean
    public String message() {
        return "message";
    }

    private static void lookUpBeanByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }
}
