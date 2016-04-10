package com.domain;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.persistence.UserDAO;
import com.persistence.User;
import com.domain.HibernateUtils;

public class UserManager implements UserDAO {

    //保存
    @Override
    public void saveUser(User u) {
        Session s = null;
        Transaction tr = null;
        try{
            s = HibernateUtils.getSession();
            tr = s.beginTransaction();//开启事务
            s.save(u);
            s.persist(u);		 
            tr.commit();
            s.refresh(u);
        }catch(Exception e){
            if(tr!=null){
                tr.rollback();
            }
            e.printStackTrace();//如果写throw e的话，接口中的方法也要定义成抛出异常
        }finally{
            if(s!=null){
                s.close();//一定要close
            }
        }
    }

    //删除
    @Override
    public void delUser(User u) {
        Session s = null;
        Transaction tr = null;
        try{
            s = HibernateUtils.getSession();
            tr = s.beginTransaction();
            s.delete(u);
            tr.commit();
        }catch(Exception e){
            if(tr!=null){
                tr.rollback();
            }
            e.printStackTrace();
        }finally{
            if(s!=null){
                s.close();
            }
        }
    }

    //更新
    @Override
    public void updateUser(User u) {
        Session s = null;
        Transaction tr = null;
        try{
            s = HibernateUtils.getSession();
            tr = s.beginTransaction();
            s.update(u);
            tr.commit();
        }catch(Exception e){
            if(tr!=null){
                tr.rollback();
            }
            e.printStackTrace();
        }finally{
            if(s!=null){
                s.close();
            }
        }
    }

    //根据id查找
    @Override
    public User findUserById(int id) {
        User u = null;
        Session s = null;
        try{
            s = HibernateUtils.getSession();
            u = (User)s.get(User.class, id);//默认就是根据id查找
            
            //懒加载
//            u = (User)s.load(User.class, id);//load方式的返回值永远都不可能是空,load不会立刻去访问数据库，直到第一次去使用时才访问
//            System.out.println(u.getName()+" "+u.getBirthday());//如果没有这一句打印语句会报错！懒加载的异常
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(s!=null){
                s.close();
            }
        }
        return u;
    }

    //根据name查找
    @Override
    public User findUserByName(String name) {
        User u = null;
        Session s = null;
        try{
            s = HibernateUtils.getSession();
            
            //方法一：hql中使用？
//            String hql = "from User as user where user.name=?";
//            Query query = s.createQuery(hql);
//            query.setString(0, name);
            
            //方法二：使用替代字符
            String hql = "from User as user where user.name=:name";
            Query query = s.createQuery(hql);
            query.setString("name", name);
            
            //实现分页效果，这个是可移植的，不管使用的那一种数据库，通过配置文件中的 Dialect来完成
//            query.setFirstResult(0);
//            query.setMaxResults(10);
            
            //如果满足查询条件的结果很多
//            List<User> list = query.list();
//            for(User user:list){
//                System.out.println(user.getName());
//            }
            
            
            //如果只有一个
            u = (User)query.uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(s!=null){
                s.close();
            }
        }
        return u;
    }

}
