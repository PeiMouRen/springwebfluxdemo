package com.rhythm.webfluxdemo.service;

import com.rhythm.webfluxdemo.entities.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author peixi
 * @Date 2022/1/20 17:33
 * @Description
 */
public interface IUserService {

    Mono<User> getUserById(Integer id);

    Flux<User> getAllUser();

    Mono<Void> addUser(Mono<User> userMono);
}
