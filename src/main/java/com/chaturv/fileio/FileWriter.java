package com.chaturv.fileio;


import java.io.IOException;

public interface FileWriter<T> {

    public void write(T t, String path) throws IOException;
}
