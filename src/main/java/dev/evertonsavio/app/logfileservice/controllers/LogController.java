package dev.evertonsavio.app.logfileservice.controllers;

import dev.evertonsavio.app.logfileservice.models.LogRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
public class LogController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/")
    public Mono<String> healthCheck(){
        return Mono.just("I'm alive");
    }

    @GetMapping("/log-file-service/")
    public Mono<String> endpointCheck(){
        return Mono.just("I'm alive");
    }

    @PostMapping("/log-file-service/v1/{mac}")
    public Mono<String> logByMac(@PathVariable String mac, @RequestBody LogRequest logRequest){
        if(logRequest.getPayload() == null | logRequest.getMac() == null | !mac.equals(logRequest.getMac())){
            return Mono.error(new ResponseStatusException(HttpStatus.FORBIDDEN));
        }

        logger.info("{\"mac\":\"" + mac + "\", \"payload\":\"" + logRequest.getPayload() + "\", \"date\":\""
                + new Date() + "\"}");

        return Mono.just("OK");
    }

}
