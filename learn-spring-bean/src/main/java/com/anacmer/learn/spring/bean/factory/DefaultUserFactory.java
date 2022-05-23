package com.anacmer.learn.spring.bean.factory;

import com.anacmer.learn.spring.pojo.User;

/**
 * @Author ZhaoJQ
 * @Date 2022/5/23 22:19
 */
public class DefaultUserFactory implements UserFactory {

    @Override
    public User createUser() {
        return User.createUer();
    }
}
