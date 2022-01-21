package com.rhythm.webfluxdemo.handler;

import com.rhythm.webfluxdemo.entities.User;
import com.rhythm.webfluxdemo.service.IUserService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author peixi
 * @Date 2022/1/21 9:30
 * @Description
 */
public class UserHandler {

    private final IUserService userService;

    public UserHandler(IUserService userService) {
        this.userService = userService;
    }

    public Mono<ServerResponse> getUserById(ServerRequest serverRequest) {
        Integer userId = Integer.valueOf(serverRequest.pathVariable("id"));
        Mono<User> userMono = userService.getUserById(userId);
        return userMono.flatMap(user -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(user)));

    }

    public Mono<ServerResponse> getAllUser(ServerRequest serverRequest) {
        Flux<User> users = userService.getAllUser();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(users, User.class);
    }

    public Mono<ServerResponse> addUser(ServerRequest serverRequest) {
        Mono<User> userMono = serverRequest.bodyToMono(User.class);
        return ServerResponse.ok().build(userService.addUser(userMono));
    }

}
