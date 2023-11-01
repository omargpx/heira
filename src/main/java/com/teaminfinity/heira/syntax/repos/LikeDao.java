package com.teaminfinity.heira.syntax.repos;

import com.teaminfinity.heira.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface LikeDao extends JpaRepository<Like,Integer> {

    List<Like> findAllByRoute(Route route);
    Integer countAllByRoute(Route route);
    List<Like> findAllByUser(User user);
}
