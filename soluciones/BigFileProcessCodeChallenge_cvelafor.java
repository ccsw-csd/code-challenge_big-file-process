package com.cca.codechallenge;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BigFileProcessCodeChallenge {

    /** NO MODIFICAR ESTA PARTE **/

    public static void main(String[] args) throws Exception {

        String filename = "./data.txt";

        if (args.length == 1)
            filename = args[0];

        long start = System.nanoTime();
        new BigFileProcessCodeChallenge().init(filename);
        long end = System.nanoTime();

        System.out.println("Duracion: " + (end - start) / 1000000000.0 + " s");
    }

    /*****************************/

    public void init(String filename) throws Exception {

        extractStatistics(readDataFile(filename));
    }

    private void extractStatistics(ConcurrentMap<String, ConcurrentMap<String, Integer>> map) {

        Optional<ConcurrentMap.Entry<String, Integer>> womenMax = map.get("Mujer").entrySet().parallelStream().max(ConcurrentMap.Entry.comparingByValue());
        System.out.println("La mujer mas repetida esta en '" + womenMax.get().getKey().split("-")[0] + "', se llama '" + womenMax.get().getKey().split("-")[1] + "' y aparece '"
                + womenMax.get().getValue() + "' veces");

        Optional<ConcurrentMap.Entry<String, Integer>> womenMin = map.get("Mujer").entrySet().parallelStream().min(ConcurrentMap.Entry.comparingByValue());
        System.out.println("La mujer menos repetida esta en '" + womenMin.get().getKey().split("-")[0] + "', se llama '" + womenMin.get().getKey().split("-")[1] + "' y aparece '"
                + womenMin.get().getValue() + "' veces");

        Optional<ConcurrentMap.Entry<String, Integer>> menMax = map.get("Hombre").entrySet().parallelStream().max(ConcurrentMap.Entry.comparingByValue());
        System.out.println("El hombre mas repetido esta en '" + menMax.get().getKey().split("-")[0] + "', se llama '" + menMax.get().getKey().split("-")[1] + "' y aparece '"
                + menMax.get().getValue() + "' veces");

        Optional<ConcurrentMap.Entry<String, Integer>> menMin = map.get("Hombre").entrySet().parallelStream().min(ConcurrentMap.Entry.comparingByValue());
        System.out.println("El hombre menos repetida esta en '" + menMin.get().getKey().split("-")[0] + "', se llama '" + menMin.get().getKey().split("-")[1] + "' y aparece '"
                + menMin.get().getValue() + "' veces");
    }

    private ConcurrentMap<String, ConcurrentMap<String, Integer>> readDataFile(String filename) throws IOException {

        try (Stream<String> lines = java.nio.file.Files.lines(Paths.get(filename))) {
            return lines.parallel().map(line -> line.split(";")).collect(Collectors.groupingByConcurrent(line -> line[1],
                    Collectors.groupingByConcurrent(line -> line[0] + "-" + line[2], Collectors.summingInt(e -> Integer.parseInt(e[5])))));
        }
    }

}
