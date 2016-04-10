package com.main;

import java.util.Date;

import com.persistence.User;
import com.domain.UserManager;

public class Test {
	 public static void main(String arg[]){
        //插入两条数据
        User u = new User();
        u.setName("test");
        u.setBirthday(new Date());
        
        User u0 = new User();
        u0.setName("hibernate");
        u0.setBirthday(new Date());
        
        UserManager um = new UserManager();
        um.saveUser(u);
        um.saveUser(u0);
        
        User u1 = new User();
        User u2 = new User(); 
        
        //测试 findUserByName 和 delUser 方法
        String name = "test";
        u1 = um.findUserByName(name);
        if(u1!=null){
            System.out.println(u1.getId()+" "+u1.getName());
        }else{
            System.out.println("不存在该用户！");
        }
        
        //测试 findUserById 和  updateUser 方法
        int id = 2;
        u2 = um.findUserById(id);
        System.out.println(u2.getId()+" "+u2.getName());
        u2.setName("java");
        u2.setBirthday(new Date());
        um.updateUser(u2);
        u2 = um.findUserById(id);
        System.out.println(u2.getId()+" "+u2.getName());
        
        System.out.println("OK");
    }
}
