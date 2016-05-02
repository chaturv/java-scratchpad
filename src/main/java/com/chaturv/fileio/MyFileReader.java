package com.chaturv.fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class MyFileReader implements FileReader<String> {

    @Override
    public String readFile(String path) throws IOException {
        File f = new File(path);
        BufferedReader reader = new BufferedReader(new java.io.FileReader(f));

        String line = null;

        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        return null;
    }
}
