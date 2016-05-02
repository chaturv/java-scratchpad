package com.chaturv.fileio;


import java.io.IOException;

public interface FileReader<T> {

    public T readFile(String filePath) throws IOException;

}
