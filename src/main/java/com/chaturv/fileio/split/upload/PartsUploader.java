package com.chaturv.fileio.split.upload;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PartsUploader {

    private String URL_TEMPLATE = "http://localhost:8080/upload/{uploadID}/{name}";

    private RestTemplate template;

    public PartsUploader() {
        List<HttpMessageConverter<?>> converters = new ArrayList<>();

        converters.add(new FormHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());
        converters.add(new MappingJackson2HttpMessageConverter());

        template = new RestTemplate();
        template.setMessageConverters(converters);
    }

    public void uploadParts(List<Path> paths, String uploadId) {
        for (Path path : paths) {
            //replace . by _
            String fileName = path.getFileName().toString();
            String replace = fileName.replace('.', '_');

            MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
            parts.add("file", new FileSystemResource(path.toFile()));

            UploadStatus status = template.postForObject(URL_TEMPLATE, parts, UploadStatus.class, uploadId, replace);

            System.out.println(status);
        }
    }

    public static void main(String[] args) {
        List<Path> splits = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            splits.add(Paths.get("C:/Work/data/Five-C-s-of-Cinematography.pdf-" + i));
        }

        new PartsUploader().uploadParts(splits, UUID.randomUUID().toString());
    }
}
