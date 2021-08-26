//package dev.evertonsavio.app.logfileservice.controllers;
//
//import com.google.gson.Gson;
//import dev.evertonsavio.app.logfileservice.models.LogResponse;
//import reactor.core.publisher.Flux;
//
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Date;
//import java.util.stream.Stream;
//
//public class FileReaderTest {
//
//    public static void main(String[] args) {
//
//        Path ipPath = Paths.get("./00112233445566.txt");
//
//        Flux<String> stringFlux = Flux.using(
//                () -> Files.lines(ipPath),
//                Flux::fromStream,
//                Stream::close
//        );
//
////        stringFlux.doOnNext(System.out::println).subscribe();
//
//        Flux<LogResponse> logResponseFlux = stringFlux.flatMap(s -> {
//            Gson gson = new Gson();
//            LogResponse logResponse = gson.fromJson(s, LogResponse.class);
//            return Flux.just(logResponse);
//        });
//
//        logResponseFlux.doOnNext(logResponse -> System.out.println(new Date(logResponse.getDate()))).subscribe();
//
//    }
//
//}
