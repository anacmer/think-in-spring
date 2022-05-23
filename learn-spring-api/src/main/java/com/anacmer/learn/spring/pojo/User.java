package com.anacmer.learn.spring.pojo;

/**
 * @Author ZhaoJQ
 * @Date 2022/5/17 23:30
 */
public class User {
    private String userId;

    private String name;

    public static User createUer() {
        User user = new User();
        user.setUserId("static-method");
        user.setName("zhaojq-s");
        return user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
