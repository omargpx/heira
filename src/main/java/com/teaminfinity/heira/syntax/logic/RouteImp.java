package com.teaminfinity.heira.syntax.logic;

import com.teaminfinity.heira.entity.*;
import com.teaminfinity.heira.syntax.repos.RouteDao;
import com.teaminfinity.heira.syntax.repos.LocationDao;
import com.teaminfinity.heira.syntax.service.HiraMethods;
import com.teaminfinity.heira.syntax.service.RouteService;
import com.teaminfinity.heira.utils.exceptions.HiraException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.teaminfinity.heira.utils.model.RouteType;
import org.springframework.stereotype.Service;

import java.lang.Double;
import java.util.List;
import java.util.Optional;

@Service
public class RouteImp implements RouteService{

    @Autowired
    private RouteDao repo;
    @Autowired
    private LocationDao locationRepo;
    @Autowired
    private HiraMethods hira;

    @Override
    public List<Route> getAll(){
        return repo.findAll();
    }

    @Override
    public Route getById(int id){
        return repo.findById(id).orElseThrow(()-> new HiraException("ROUTE_SERVICE","route not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Route save(Route route){
        route.setTag(findMaxVal(route));
        route.setStart(locationRepo.save(route.getStart()));
        route.setEnd(locationRepo.save(route.getEnd()));
        return repo.save(route);
    }

    @Override
    public List<Route> getByTagEnviroment(RouteType tag){
        return repo.findAllByTag(tag);
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