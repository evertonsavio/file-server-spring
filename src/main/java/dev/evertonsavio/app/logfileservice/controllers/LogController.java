package dev.evertonsavio.app.logfileservice.controllers;

import com.google.gson.Gson;
import dev.evertonsavio.app.logfileservice.models.LogRequest;
import dev.evertonsavio.app.logfileservice.models.LogResponse;
import dev.evertonsavio.app.logfileservice.models.LogResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.stream.Stream;

@RestController
public class LogController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    public static final String BASE_PATH = "./logs/";

    @GetMapping("/")
    public Mono<String> healthCheck(){
        return Mono.just("I'm alive");
    }

    @GetMapping("/log-file-service/")
    public Mono<String> endpointCheck(){
        return Mono.just("I'm alive");
    }

    @PostMapping("/log-file-service/v1/log/{mac}")
    public Mono<String> logByMac(@PathVariable String mac, @RequestBody LogRequest logRequest){
        if(logRequest.getPayload() == null | logRequest.getMac() == null | !mac.equals(logRequest.getMac())){
            return Mono.error(new ResponseStatusException(HttpStatus.FORBIDDEN));
        }

        String text = "{\"mac\":\"" + mac + "\", \"payload\":\"" + logRequest.getPayload()
                + "\", \"date\":" + new Date().getTime() + "}";

        Path basePath = Paths.get(BASE_PATH);
        if(!Files.isWritable(basePath)){
            try {
                Files.createDirectory(basePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        logger.info(text);
        String PATH = BASE_PATH + mac.replace(":", "");

        File file = new File(PATH);
        FileWriter fr = null;
        BufferedWriter br = null;
//        long lines = 0;

        try {
            // to append to file, you need to initialize FileWriter using below constructor
            fr = new FileWriter(file, true);
            br = new BufferedWriter(fr);
            Path path = Paths.get(PATH);
            if(Files.lines(path).count() > 0){
                br.newLine();
            }
            br.write(text);
//            for (int i = 0; i < 5; i++) {
//                br.newLine();
//                // you can use write or append method
//                br.write(text);
//            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return Mono.just("OK");
    }

    @GetMapping("/log-file-service/v1/file/{mac}")
    public Flux<LogResponseDto> getLogByMac(@PathVariable String mac){

        Path ipPath = Paths.get("./logs/" + mac.replace(":", ""));

        if(!Files.isWritable(ipPath)){
            return Flux.empty();
        }

        Flux<String> stringFlux = Flux.using(
                () -> Files.lines(ipPath),
                Flux::fromStream,
                Stream::close
        );

//        stringFlux.doOnNext(System.out::println).subscribe();

        Flux<LogResponseDto> logResponseFlux = stringFlux.flatMap(s -> {
            Gson gson = new Gson();
            LogResponse logResponse = gson.fromJson(s, LogResponse.class);
            return Flux.just(new LogResponseDto(logResponse.getMac(), logResponse.getPayload(), new Date(logResponse.getDate())));
        });

        return logResponseFlux; //.doOnNext(logResponse -> System.out.println(new Date(logResponse.getDate())));
    }

}
