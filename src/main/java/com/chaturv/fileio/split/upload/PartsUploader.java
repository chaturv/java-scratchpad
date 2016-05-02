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
import java.util.ArrayList;
import java.util.List;

public class PartsUploader {

    String URL_TEMPLATE = "http://localhost:8080/uploadFile?uploadID=%s";

    RestTemplate template;
    List<HttpMessageConverter<?>> converters = new ArrayList<>();

    public PartsUploader() {
        converters.add(new FormHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());
        converters.add(new MappingJackson2HttpMessageConverter());

        template = new RestTemplate();
        template.setMessageConverters(converters);
    }

    public void uploadParts(List<Path> paths, String uploadId) {
        String url = String.format(URL_TEMPLATE, uploadId);

        for (Path path : paths) {
            MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
            parts.add("uploadID", uploadId);
            parts.add("file", new FileSystemResource(path.toFile()));

            String response = template.postForObject(url, parts, String.class);
        }
    }
}
