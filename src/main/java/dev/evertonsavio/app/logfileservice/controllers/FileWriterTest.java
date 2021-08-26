//package dev.evertonsavio.app.logfileservice.controllers;
//
//import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Date;
//
//public class FileWriterTest {
//
//    public static final String PATH = "./00112233445566.txt";
//
//    public static void main(String[] args) throws IOException {
//
//        String text = "{\"mac\":\"" + "00:11:22:33:44:55:66" + "\", \"payload\":\"" + "PAYLOAD"
//                + "\", \"date\":" + new Date().getTime() + "}";
//
//        File file = new File(PATH);
//        FileWriter fr = null;
//        BufferedWriter br = null;
////        long lines = 0;
//
//        try {
//            // to append to file, you need to initialize FileWriter using below constructor
//            fr = new FileWriter(file, true);
//            br = new BufferedWriter(fr);
//            Path path = Paths.get(PATH);
//            if(Files.lines(path).count() > 0){
//                br.newLine();
//            }
//            br.write(text);
////            for (int i = 0; i < 5; i++) {
////                br.newLine();
////                // you can use write or append method
////                br.write(text);
////            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                br.close();
//                fr.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
