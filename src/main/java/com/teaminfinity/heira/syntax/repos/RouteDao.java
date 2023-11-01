package com.teaminfinity.heira.syntax.repos;

import com.teaminfinity.heira.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.teaminfinity.heira.utils.model.RouteType;

import java.util.Optional;
import java.util.List;

@Repository
public interface RouteDao extends JpaRepository<Route,Integer> {
    List<Route> findAllByTag(RouteType tag);
}
