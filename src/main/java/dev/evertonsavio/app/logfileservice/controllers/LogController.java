package dev.evertonsavio.app.logfileservice.controllers;

import dev.evertonsavio.app.logfileservice.models.LogRequest;
import dev.evertonsavio.app.logfileservice.models.LogResponseDto;
import dev.evertonsavio.app.logfileservice.services.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/")
    public Mono<String> healthCheck(){
        return Mono.just("I'm alive");
    }

    @GetMapping("/log-file-service/")
    public Mono<String> endpointCheck(){
        return Mono.just("I'm alive");
    }

    @GetMapping("/log-file-service/v1/list")
    public Mono<String[]> listLogs(){

        return logService.listPaths();

    }

    @PostMapping("/log-file-service/v1/log/{mac}")
    public Mono<String> logByMac(@PathVariable String mac, @RequestBody LogRequest logRequest){
        if(logRequest.getPayload() == null | logRequest.getMac() == null | !mac.equals(logRequest.getMac())){
            return Mono.error(new ResponseStatusException(HttpStatus.FORBIDDEN));
        }

        return logService.processLog(mac, logRequest);
    }

    @GetMapping("/log-file-service/v1/file/{mac}")
    public Flux<LogResponseDto> getLogByMac(@PathVariable String mac){

        return logService.getLogByMac(mac);
    }

}
