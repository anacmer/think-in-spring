package com.anacmer.learn.spring.bean.factory;

import com.anacmer.learn.spring.pojo.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Author ZhaoJQ
 * @Date 2022/5/23 22:23
 */
public class UserFactoryBean implements FactoryBean<User> {


    @Override
    public User getObject() {
        return User.createUer();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
