package com.anacmer.learn.spring.dependency.injection;

import com.anacmer.learn.spring.pojo.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 基于 Java 注解实现 资源的依赖  方法注入示例
 *
 * @Author ZhaoJQ
 * @Date 2022/6/14 23:41
 */
@Configuration
public class AnnotationDependencySetterInjectionDemo {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext()) {
            XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
            applicationContext.register(AnnotationDependencySetterInjectionDemo.class);
            String resourcePath = "classpath:META-INF/dependency-injection-content.xml";
            // 加载 XML 资源，解析并生成 BeanDefinition
            xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);
            applicationContext.refresh();
            // 依赖查找并且创建 Bean
            UserHolder userHolder = applicationContext.getBean(UserHolder.class);
            System.out.println(userHolder);

        }
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
