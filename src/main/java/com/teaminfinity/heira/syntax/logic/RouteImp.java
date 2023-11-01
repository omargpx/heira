package com.teaminfinity.heira.syntax.logic;

import com.teaminfinity.heira.entity.*;
import com.teaminfinity.heira.syntax.repos.LikeDao;
import com.teaminfinity.heira.syntax.repos.RouteDao;
import com.teaminfinity.heira.syntax.repos.LocationDao;
import com.teaminfinity.heira.syntax.repos.UserDao;
import com.teaminfinity.heira.syntax.service.HiraMethods;
import com.teaminfinity.heira.syntax.service.RouteService;
import com.teaminfinity.heira.utils.exceptions.HiraException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.teaminfinity.heira.utils.model.RouteType;
import org.springframework.stereotype.Service;

import java.lang.Double;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RouteImp implements RouteService{

    @Autowired
    private RouteDao repo;
    @Autowired
    private LocationDao locationRepo;
    @Autowired
    private LikeDao likeRepo;
    @Autowired
    private UserDao userRepo;
    @Autowired
    private HiraMethods hira;

    @Override
    public List<Route> getAll(){
        List<Route> routes = repo.findAll();
        routes.forEach(r->{
            int likes = likeRepo.countAllByRoute(r);
            r.setLikes(likes);
        });
        return routes;
    }

    @Override
    public Route getById(int id){
        var route = repo.findById(id).orElseThrow(()-> new HiraException("ROUTE_SERVICE","route not found", HttpStatus.NOT_FOUND));
        int likes = likeRepo.countAllByRoute(route);
        route.setLikes(likes);
        return route;
    }

    @Override
    public Route save(Route route){
        route.setTag(findMaxVal(route));
        route.setStart(locationRepo.save(route.getStart()));
        route.setEnd(locationRepo.save(route.getEnd()));
        return repo.save(route);
    }

    @Override
    public List<Route> getByTagEnvironment(RouteType tag){
        List<Route> routes = repo.findAllByTag(tag);
        routes.forEach(r -> {
            int likes = likeRepo.countAllByRoute(r);
            r.setLikes(likes);
        });
        return routes;
    }

    @Override
    public Object setUserLike(Integer routeId, String hiraId) {
        var user = userRepo.findByHiraId(hiraId);
        Route route = repo.findById(routeId).orElseThrow(()-> new HiraException("ROUTE_SERVICE","route not found", HttpStatus.NOT_FOUND));
        if(user.isEmpty())
            throw new  HiraException("ROUTE_SERVICE","user doesn't exists", HttpStatus.NOT_FOUND);
        return likeRepo.save(Like.builder().route(route).user(user.get()).date(LocalDate.now()).build());
    }

    private RouteType findMaxVal(Route route){
        double max = Double.MIN_VALUE;
        double high = 6.5;
        RouteType tag = null;

        if (route.getNoise() > max) {
            max = route.getNoise();
            tag = RouteType.NOISY;
        }
        if (route.getAirquality() > max) {
            max = route.getAirquality();
            tag = RouteType.QUALITY_AIR;
        }
        if (route.getForestation() > max) {
            max = route.getForestation();
            tag = RouteType.FORESTATION;
        }
        if (route.getForestation() > high && route.getAirquality() > high)
            tag = RouteType.QUIET;
        return tag;
    }

}