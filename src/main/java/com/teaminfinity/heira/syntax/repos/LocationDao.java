package com.teaminfinity.heira.syntax.repos;

import com.teaminfinity.heira.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationDao extends JpaRepository<Location,Integer> {
}
