package com.teaminfinity.heira.syntax.logic;

import com.teaminfinity.heira.entity.Like;
import com.teaminfinity.heira.entity.User;
import com.teaminfinity.heira.syntax.repos.LikeDao;
import com.teaminfinity.heira.syntax.repos.UserDao;
import com.teaminfinity.heira.syntax.service.HiraMethods;
import com.teaminfinity.heira.syntax.service.UserService;
import com.teaminfinity.heira.utils.exceptions.HiraException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserImp implements UserService {

    @Autowired
    private UserDao repo;
    @Autowired
    private HiraMethods hira;
    @Autowired
    private LikeDao likeRepo;

    @Override
    public List<User> getAll() {
        return repo.findAll();
    }

    @Override
    public User getById(int id) {
        return repo.findById(id).orElseThrow(()-> new HiraException("USER_SERVICE","User not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public User save(User user) {
        var verify = repo.findByUsername(user.getUsername());
        if(verify.isPresent())
            throw new HiraException("USER_SERVICE","User Already exists", HttpStatus.BAD_REQUEST);
        user.setHiraId(hira.HiraId());
        return repo.save(user);
    }

    @Override
    public User filter(String query) {
        Optional<User> user;
        user = repo.findByUsername(query);
        if(user.isPresent())
            return user.get();
        user = repo.findByHiraId(query);
        if (user.isPresent())
            return user.get();
        throw new HiraException("USER_SERVICE","User not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public List<Like> getLikesByUserId(String hiraId) {
        var user = repo.findByHiraId(hiraId);
        if(user.isEmpty())
            throw new HiraException("USER_SERVICE","User not found", HttpStatus.NOT_FOUND);
        return likeRepo.findAllByUser(user.get());
    }
}
