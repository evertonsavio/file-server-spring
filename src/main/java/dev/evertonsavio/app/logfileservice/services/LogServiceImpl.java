package dev.evertonsavio.app.logfileservice.services;

import com.google.gson.Gson;
import dev.evertonsavio.app.logfileservice.models.LogRequest;
import dev.evertonsavio.app.logfileservice.models.LogResponse;
import dev.evertonsavio.app.logfileservice.models.LogResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
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

@Service
public class LogServiceImpl implements LogService{

    public static final String BASE_PATH = "./logs/";
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Mono<String[]> listPaths() {

        Path ipPath = Paths.get("./logs");

        if(!Files.isWritable(ipPath)){
            return Mono.empty();
        }

        File f = new File("./logs");
        String[] listOfPaths = f.list();

        return Mono.just(listOfPaths);
    }

    @Override
    public Mono<String> processLog(String mac, LogRequest logRequest) {

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
        String PATH = BASE_PATH + mac;

        File file = new File(PATH);
        FileWriter fr = null;
        BufferedWriter br = null;

        try {
            fr = new FileWriter(file, true);
            br = new BufferedWriter(fr);
            Path path = Paths.get(PATH);
            if(Files.lines(path).count() > 0){
                br.newLine();
            }
            br.write(text);

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

    @Override
    public Flux<LogResponseDto> getLogByMac(String mac) {

        Path ipPath = Paths.get("./logs/" + mac);

        if(!Files.isWritable(ipPath)){
            return Flux.empty();
        }

        Flux<String> stringFlux = Flux.using(
                () -> Files.lines(ipPath),
                Flux::fromStream,
                Stream::close
        );

        return stringFlux.flatMap(s -> {
            Gson gson = new Gson();
            LogResponse logResponse = gson.fromJson(s, LogResponse.class);
            return Flux.just(new LogResponseDto(logResponse.getMac(), logResponse.getPayload(),
                    new Date(logResponse.getDate())));
        });

    }


}
