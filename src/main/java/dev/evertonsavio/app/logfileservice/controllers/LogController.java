package dev.evertonsavio.app.logfileservice.controllers;

import dev.evertonsavio.app.logfileservice.models.LogRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestController
public class LogController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/log-file-service/{mac}")
    public Mono<String> logByMac(@PathVariable String mac, @RequestBody LogRequest logRequest){
        if(!mac.equals(logRequest.getMac())){
            return Mono.error(new ResponseStatusException(HttpStatus.FORBIDDEN));
        }

        logger.info("{\"mac\":\"" + mac + "\", \"payload\":\"" + logRequest.getPayload() + "\"}");
        return Mono.just("Hello from log controller;" + mac);
    }

}
