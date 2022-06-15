package com.anacmer.learn.spring.dependency.injection;

import com.anacmer.learn.spring.pojo.User;

/**
 * @Author ZhaoJQ
 * @Date 2022/6/14 23:46
 */
public class UserHolder {

    private User user;

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
