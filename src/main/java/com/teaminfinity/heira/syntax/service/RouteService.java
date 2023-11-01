package com.teaminfinity.heira.syntax.service;

import com.teaminfinity.heira.entity.Route;
import com.teaminfinity.heira.utils.model.RouteType;

import java.util.List;

public interface RouteService {
    List<Route> getAll();
    Route getById(int id);
    Route save(Route route);

    //filter
    List<Route> getByTagEnvironment(RouteType tag);

    Object setUserLike(Integer routeId, String hiraId);
}
