package com.chaturv.fileio.split;


import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads splits and produces a new file called destName
 */
public class ReadSplits {

    public void readSplits(List<String> splitNames, String destName) throws IOException {
        Path path = Paths.get(destName);

        try (OutputStream outputStream = Files.newOutputStream(path)) {
            for (String splitName : splitNames) {
                Path splitPath = Paths.get(splitName);
                InputStream inputStream = Files.newInputStream(splitPath);

                StreamUtils.copy(inputStream, outputStream);
                inputStream.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        List<String> splitNames = new ArrayList<String>();
//        for (int i = 0; i < 12; i++) {
//            splitNames.add("C:/Work/data/upload/Five-C-s-of-Cinematography_pdf-" + i);
//        }

        for (int i = 0; i < 45; i++) {
            splitNames.add("/home/vineet/work/data/upload_test/ideaIC-15.0.2.tar.gz-" + i);
        }

//        new ReadSplits().readSplits(splitNames, "C:/Work/data/upload/Five-C-s-of-Cinematography_copy.pdf");
        new ReadSplits().readSplits(splitNames, "/home/vineet/work/data/upload_test/ideaIC-15.0.2_COPY.tar.gz");

    }
}

