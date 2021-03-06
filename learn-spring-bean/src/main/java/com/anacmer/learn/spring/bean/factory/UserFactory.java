package com.anacmer.learn.spring.bean.factory;

import com.anacmer.learn.spring.pojo.User;

/**
 * @Author ZhaoJQ
 * @Date 2022/5/23 22:18
 */
public interface UserFactory {

    default User createUser() {
        return User.createUer();
    }
    void initUserFactory();

    void doDestroy();
}
