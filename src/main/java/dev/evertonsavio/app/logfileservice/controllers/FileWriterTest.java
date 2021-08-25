package dev.evertonsavio.app.logfileservice.controllers;

import java.io.*;

public class FileWriterTest {

    public static final String PATH = "./00:11:22:33:44:55:66";

    public static void main(String[] args) throws IOException {

        String text = "This is the text to append";

        File file = new File(PATH);
        FileWriter fr = null;
        BufferedWriter br = null;
        try {
            // to append to file, you need to initialize FileWriter using below constructor
            fr = new FileWriter(file, true);
            br = new BufferedWriter(fr);
            br.newLine();
            br.write(text);
            br.write(",");
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
    }
}
