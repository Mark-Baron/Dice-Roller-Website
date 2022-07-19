package com.dicewebapp.dao;

import com.dicewebapp.model.User;

public interface UserDao {

    boolean create (String username, String password);

    User findByUsername(String username);
}
