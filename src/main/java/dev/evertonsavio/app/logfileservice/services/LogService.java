package dev.evertonsavio.app.logfileservice.services;

import dev.evertonsavio.app.logfileservice.models.LogRequest;
import dev.evertonsavio.app.logfileservice.models.LogResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LogService {

    Mono<String[]> listPaths();
    Mono<String> processLog(String mac, LogRequest logRequest);
    Flux<LogResponseDto> getLogByMac(String mac);

}
