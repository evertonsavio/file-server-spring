package dev.evertonsavio.app.logfileservice.controllers;

import reactor.core.publisher.Flux;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReaderTest {

    public static void main(String[] args) {

        Path ipPath = Paths.get("./00:11:22:33:44:55:66");

        Flux<String> stringFlux = Flux.using(
                () -> Files.lines(ipPath),
                Flux::fromStream,
                Stream::close
        );

        stringFlux.doOnNext(System.out::println).subscribe();

    }

}
