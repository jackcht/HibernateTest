package com.persistence;

public interface  UserDAO {
	public void saveUser(User u);
    public void delUser(User u);
    public void updateUser(User u);
    public User findUserById(int id);
    public User findUserByName(String name);
}


