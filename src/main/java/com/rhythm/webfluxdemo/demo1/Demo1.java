package com.rhythm.webfluxdemo.demo1;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Author peixi
 * @Date 2022/1/20 11:20
 * @Description
 */
public class Demo1 {
    public static void main(String[] args) {
        Mono.just(1).subscribe(System.out::println);
        Flux.just(1,2,3).subscribe(System.out::println);
        List list = new ArrayList();
        list.forEach(System.out::println);
    }
}
