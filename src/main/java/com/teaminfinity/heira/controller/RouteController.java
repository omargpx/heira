package com.teaminfinity.heira.controller;

import com.teaminfinity.heira.entity.Route;
import com.teaminfinity.heira.syntax.service.HiraMethods;
import com.teaminfinity.heira.syntax.service.RouteService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.teaminfinity.heira.utils.model.RouteType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/route")
public class RouteController {

    @Autowired
    private RouteService service;
    @Autowired
    private HiraMethods hira;
    private final String svr_name = "ROUTE_SERVICE";

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(name="tag",required=false)RouteType tag,
                                    @RequestParam(name="id",required=false) Integer id,
                                    HttpServletRequest request){
        if (null!=tag)
            return ResponseEntity.ok(hira.getResponse(request,svr_name,service.getByTagEnvironment(tag),HttpStatus.OK));
        if(null!=id)
            return ResponseEntity.ok(hira.getResponse(request,svr_name,service.getById(id),HttpStatus.OK));
        return ResponseEntity.ok(hira.getResponse(request,svr_name,service.getAll(),HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Route route,HttpServletRequest request){
                return ResponseEntity.ok(hira.getResponse(request,svr_name,service.save(route),HttpStatus.OK));
    }

    @PostMapping("/{routeId}/like")
    public ResponseEntity<?> saveUserLikeToRoute(@PathVariable Integer routeId,
                                                 @RequestParam(name = "hId")String hiraId,
                                                 HttpServletRequest request){
        return ResponseEntity.ok(hira.getResponse(request,svr_name,service.setUserLike(routeId,hiraId),HttpStatus.OK));
    }
}