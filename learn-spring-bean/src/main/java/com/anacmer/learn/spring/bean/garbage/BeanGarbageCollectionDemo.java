package com.anacmer.learn.spring.bean.garbage;

import com.anacmer.learn.spring.bean.factory.DefaultUserFactory;
import com.anacmer.learn.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Bean 垃圾回收（GC）Demo
 *
 * @Author ZhaoJQ
 * @Date 2022/6/6 21:38
 */
@Configuration
public class BeanGarbageCollectionDemo {

    public static void main(String[] args) throws InterruptedException {
        try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext()) {

            applicationContext.register(BeanGarbageCollectionDemo.class);
            // 启用 Spring 应用上下文
            applicationContext.refresh();

            // 关闭应用上下文
            applicationContext.close();

            Thread.sleep(5000L);
            // 强制触发GC
            System.gc();
            Thread.sleep(5000L);
            // ApplicationContext 关闭之前，GC是不会回收Bean的
            // ApplicationContext 关闭之后，JVM会在垃圾回收周期中回收掉 Bean
        }
    }

    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
