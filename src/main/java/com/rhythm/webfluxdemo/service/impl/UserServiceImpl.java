package com.rhythm.webfluxdemo.service.impl;

import com.rhythm.webfluxdemo.entities.User;
import com.rhythm.webfluxdemo.service.IUserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author peixi
 * @Date 2022/1/20 17:37
 * @Description
 */
@Service
public class UserServiceImpl implements IUserService {

    private final Map<Integer, User> userMap = new HashMap<>();

    public UserServiceImpl() {
        userMap.put(1, new User(1, "张三", "男", 18));
        userMap.put(2, new User(2, "李四", "男", 18));
        userMap.put(3, new User(3, "王五", "男", 18));
    }


    @Override
    public Mono<User> getUserById(Integer id) {
        return Mono.just(userMap.get(id));
    }

    @Override
    public Flux<User> getAllUser() {
        return Flux.fromIterable(userMap.values());
    }

    @Override
    public Mono<Void> addUser(Mono<User> userMono) {
        return userMono.doOnNext(user -> {
            Integer id = userMap.size() + 1;
            userMap.put(id, user);
        }).thenEmpty(Mono.empty());
    }
}
