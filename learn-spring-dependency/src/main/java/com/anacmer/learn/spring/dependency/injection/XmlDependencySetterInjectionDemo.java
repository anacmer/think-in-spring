package com.anacmer.learn.spring.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 基于 XML 资源的依赖 Setter 方法注入示例
 *
 * @Author ZhaoJQ
 * @Date 2022/6/14 23:41
 */
public class XmlDependencySetterInjectionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        String resourcePath = "classpath:META-INF/dependency-injection-content.xml";
        // 加载 XML 资源，解析并生成 BeanDefinition
        xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);
        // 依赖查找并且创建 Bean
        UserHolder userHolder = defaultListableBeanFactory.getBean(UserHolder.class);
        System.out.println(userHolder);
    }
}
