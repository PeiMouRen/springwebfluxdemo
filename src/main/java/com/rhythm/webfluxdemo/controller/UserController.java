package com.rhythm.webfluxdemo.controller;

import com.rhythm.webfluxdemo.entities.User;
import com.rhythm.webfluxdemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author peixi
 * @Date 2022/1/20 17:51
 * @Description
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;


    @GetMapping("/user/{id}")
    public Mono<User> user(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user")
    public Flux<User> user() {
        return userService.getAllUser();
    }

    @PostMapping("/user")
    public Mono<Void> user(User user) {
        return userService.addUser(Mono.just(user));
    }



}
