package com.teaminfinity.heira.syntax.repos;

import com.teaminfinity.heira.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByHiraId(String hiraId);
}
