package com.chaturv.fnf;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PricePointAnalysisRunner {

    public static void main(String[] args) throws URISyntaxException, IOException {
        URI uri = PricePointAnalysisRunner.class.getClassLoader().getResource("fnf/products.txt").toURI();
        List<String> lines = Files.readAllLines(Paths.get(uri));
        Set<Product> products = lines.stream()
                .map(PricePointAnalysisRunner::createProduct)
                .collect(Collectors.toSet());

        new PricePointAnalysis().analyze(products);
    }

    private static Product createProduct(String line) {
        String[] splits = line.split(",");
        if (splits.length != 4) {
            throw new RuntimeException("bad line: " + line);
        }
        return new Product(
                splits[0],
                splits[1],
                Double.parseDouble(splits[2]),
                Double.parseDouble(splits[3])
        );
    }
}
