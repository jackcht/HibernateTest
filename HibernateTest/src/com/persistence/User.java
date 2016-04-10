package com.persistence;

import java.util.Date;

public class User {
    
    private int id;
    private String name;
    private Date birthday;
    
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

}