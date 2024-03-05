//package com.edmp.api.board.controller;
//
//import com.edmp.api.board.config.CacheKey;
//
//
//import com.edmp.api.board.config.CacheStaticKey;
//import com.edmp.api.board.model.CacheTestDto;
//import kr.co.ezpmp.redis.RedisManager;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//import javax.annotation.Resource;
//
///**
// * Created by jslim
// */
//@RestController
//@Slf4j
//@RequestMapping("/redis/sample")
//@RequiredArgsConstructor
//public class RedisSampleController {
//
//    @Resource
//    private RedisManager<String> redisManager;
//
//    @Value("${ezpmp.api-server.login-api}")
//    private String test;
//
//    @Value("${ezpmp.redis.port}")
//    private int redisPort;
//
//    private final WebClient webClient;
//
//    @GetMapping("/")
//    public Mono<String> test(){
//
//        WebClient webClient2 = WebClient.create();
//        Mono<String> hello = webClient2.get()
//                .uri("http://login-api-svc.ezpmp-login-api.svc.cluster.local")
//                .retrieve()
//                .bodyToMono(String.class);
//
//        log.info(hello.flux()
//                .toStream()
//                .findFirst()
//                .orElse(null));
////
////
////        Mono<String> hello2 = webClient2.get()
////                .uri("http://login-api-svc.ezpmp-login-api.svc.cluster.local")
////                .retrieve()
////                .bodyToMono(String.class);
////
////        log.info(hello2.flux()
////                .toStream()
////                .findFirst()
////                .orElse(null));
//
//
//        return hello;
//
//    }
//
//    @GetMapping("/get")
//    public String getRedis(String test){
//
//        return redisManager.getValue( test );
//    }
//    @GetMapping("/put")
//    public void putRedis(String key, String value){
//        redisManager.put(key
//                , value
//                , CacheKey.BOARD_SAMPLE.getTimeOut()
//                , CacheKey.BOARD_SAMPLE.getTimeUnit()
//        );
//
//    }
//
//    @Cacheable(value = CacheStaticKey.CONTROLLER_SAMPLE)
//    @GetMapping("/cacheable")
//    public CacheTestDto putCacheable(){
//
//        return new CacheTestDto().setContent("contents").setSubject("subject1");
//
//    }
//}
