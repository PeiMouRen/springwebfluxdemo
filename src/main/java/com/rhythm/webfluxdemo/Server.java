package com.rhythm.webfluxdemo;

import com.rhythm.webfluxdemo.handler.UserHandler;
import com.rhythm.webfluxdemo.service.impl.UserServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.*;
import reactor.netty.http.server.HttpServer;

/**
 * @Author Xingzhe Pei
 * @Date 2022/1/21 10:25
 * @Description
 */
public class Server {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.createServer();
        System.out.println("enter to exit");
        System.in.read();
    }

    public RouterFunction<ServerResponse> routerFunction() {
        UserHandler userHandler = new UserHandler(new UserServiceImpl());

        return RouterFunctions.route(RequestPredicates.GET("/user/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::getUserById)
                .andRoute(RequestPredicates.GET("/user").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::getAllUser);
    }

    public void createServer() {
        RouterFunction<ServerResponse> router = routerFunction();
        HttpHandler httpHandler = RouterFunctions.toHttpHandler(router);
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);

        HttpServer.create().handle(adapter).bindNow();
    }
}
