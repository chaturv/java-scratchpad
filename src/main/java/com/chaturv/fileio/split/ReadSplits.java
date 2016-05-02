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
        for (int i = 0; i < 12; i++) {
            splitNames.add("C:/Work/data/Five-C-s-of-Cinematography.pdf-" + i);
        }

        new ReadSplits().readSplits(splitNames, "C:/Work/data/Five-C-s-of-Cinematography_copy.pdf");
    }
}

