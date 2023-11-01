package com.teaminfinity.heira.syntax.service;

import com.teaminfinity.heira.entity.Like;
import com.teaminfinity.heira.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(int id);
    User save(User object);

    //filter
    User filter(String query);

    List<Like> getLikesByUserId(String hiraId);
}
