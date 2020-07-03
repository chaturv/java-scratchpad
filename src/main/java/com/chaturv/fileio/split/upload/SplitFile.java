package com.chaturv.fileio.split.upload;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SplitFile {

    private static final int PART_SIZE = 5 * 1024 * 1024;

    public List<Path> split(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        InputStream inputStream = Files.newInputStream(path);

        byte[] bytes = new byte[PART_SIZE];

        List<Path> splitPaths = new ArrayList<>();

        int partCount = 0;
        int read;
        while ((read = inputStream.read(bytes)) != -1) {
            if (read < PART_SIZE) {
                byte[] residual = new byte[read];
                System.arraycopy(bytes, 0, residual, 0, residual.length);
                bytes = residual;
            }

            System.out.println("number of bytes writing : " + bytes.length);

            Path outPath = Paths.get(filePath + "-" + partCount);

            try (OutputStream outputStream = Files.newOutputStream(outPath)) {
                outputStream.write(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            partCount++;
            splitPaths.add(outPath);
        }

        System.out.println("finished");
        return splitPaths;
    }

    public static void main(String[] args) throws IOException {
//        new SplitFile().split("C:/Work/data/Five-C-s-of-Cinematography.pdf");
        new SplitFile().split("/home/vineet/work/data/ideaIC-15.0.2.tar.gz");
        //59.9 MB (62,851,160 bytes)
        //59.9 MB (62,853,120 bytes)


        //59.9 MB (62,851,160 bytes)
        //59.9 MB (62,853,120 bytes)
    }
}
