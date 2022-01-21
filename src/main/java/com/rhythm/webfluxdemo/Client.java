package com.rhythm.webfluxdemo;

import com.rhythm.webfluxdemo.entities.User;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * @Author peixi
 * @Date 2022/1/21 16:25
 * @Description
 */
public class Client {

    public static void main(String[] args) {
        WebClient webClient = WebClient.create("http://localhost:51001");

        User user = webClient.get().uri("/user/1").accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(User.class).block();
        System.out.println(user);

        Flux<User> userFlux = webClient.get().uri("/user").accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(User.class);
        userFlux.map(person -> person.getUsername()).buffer().doOnNext(System.out::println).blockFirst();
    }
}
