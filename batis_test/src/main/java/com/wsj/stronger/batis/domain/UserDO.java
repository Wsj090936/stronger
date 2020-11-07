package com.wsj.stronger.batis.domain;

/**
 *
 * @Author jiahao
 * @Date 2020/11/1 20:07
 */

public class UserDO {
    private Integer id;
    private String userName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
