package com.chaturv.fileio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyFileWriter implements FileWriter<String> {

    @Override
    public void write(String s, String path) throws IOException {
        File f = new File(path);
        FileOutputStream fos = new FileOutputStream(f);

        fos.write(s.getBytes());

        fos.flush();
        fos.close();
    }
}
