package com.cca.codechallenge;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Map<String, ProvinceData> provinceMap = new HashMap<String, ProvinceData>();

        readDataFile(filename, provinceMap);
        extractStatistics(provinceMap);

    }

    private void readDataFile(String filename, Map<String, ProvinceData> map) throws FileNotFoundException, IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(filename, StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                String parts[] = line.split(";");

                String provinceName = parts[0];
                String sex = parts[1];
                String personName = parts[2];
                String counter = parts[5];

                ProvinceData provinceData = map.get(provinceName);
                if (provinceData == null) {
                    provinceData = new ProvinceData(provinceName);
                    map.put(provinceName, provinceData);
                }

                provinceData.addData(sex, personName, counter);
            }
        }

    }

    private void extractStatistics(Map<String, ProvinceData> provinceMap) {
        List<ProvinceTopData> resumeData = new ArrayList<>();

        provinceMap.values().stream().forEach(provinceData -> {
            resumeData.add(new ProvinceTopData(provinceData));
        });

        ProvinceTopData orderMostRepeatedWoman = resumeData.stream().sorted(Comparator.comparing(ProvinceTopData::getMostRepeatedWoman, Comparator.comparing(PersonData::getCounter)).reversed()).toList().get(0);
        System.out.println("La mujer mas repetida esta en '" + orderMostRepeatedWoman.getProvinceName() + "', se llama '" + orderMostRepeatedWoman.getMostRepeatedWoman().getName() + "' y aparece '"
                + orderMostRepeatedWoman.getMostRepeatedWoman().getCounter() + "' veces");

        ProvinceTopData orderLeastRepeatedWoman = resumeData.stream().sorted(Comparator.comparing(ProvinceTopData::getLeastRepeatedWoman, Comparator.comparing(PersonData::getCounter))).toList().get(0);
        System.out.println("La mujer menos repetida esta en '" + orderLeastRepeatedWoman.getProvinceName() + "', se llama '" + orderLeastRepeatedWoman.getLeastRepeatedWoman().getName() + "' y aparece '"
                + orderLeastRepeatedWoman.getLeastRepeatedWoman().getCounter() + "' veces");

        ProvinceTopData orderMostRepeatedMan = resumeData.stream().sorted(Comparator.comparing(ProvinceTopData::getMostRepeatedMan, Comparator.comparing(PersonData::getCounter)).reversed()).toList().get(0);
        System.out.println("El hombre mas repetido esta en '" + orderMostRepeatedMan.getProvinceName() + "', se llama '" + orderMostRepeatedMan.getMostRepeatedMan().getName() + "' y aparece '"
                + orderMostRepeatedMan.getMostRepeatedMan().getCounter() + "' veces");

        ProvinceTopData orderLeastRepeatedMan = resumeData.stream().sorted(Comparator.comparing(ProvinceTopData::getLeastRepeatedMan, Comparator.comparing(PersonData::getCounter))).toList().get(0);
        System.out.println("El hombre menos repetido esta en '" + orderLeastRepeatedMan.getProvinceName() + "', se llama '" + orderLeastRepeatedMan.getLeastRepeatedMan().getName() + "' y aparece '"
                + orderLeastRepeatedMan.getLeastRepeatedMan().getCounter() + "' veces");
    }

    class PersonData {
        private String name;
        private Long counter;

        public PersonData(String name, Long counter) {
            this.name = name;
            this.counter = counter;
        }

        public String getName() {
            return name;
        }

        public Long getCounter() {
            return counter;
        }

        public void addCounter(Long counter) {
            this.counter += counter;
        }

    }

    class ProvinceData {
        private String name;

        private Map<String, PersonData> women;
        private Map<String, PersonData> men;

        public ProvinceData(String name) {
            this.name = name;
            this.women = new HashMap<String, PersonData>();
            this.men = new HashMap<String, PersonData>();
        }

        public String getName() {
            return name;
        }

        public Map<String, PersonData> getMen() {
            return men;
        }

        public Map<String, PersonData> getWomen() {
            return women;
        }

        public void addData(String sex, String personName, String counter) {

            Map<String, PersonData> map = null;

            if (sex.equals("Hombre"))
                map = men;
            else
                map = women;

            PersonData person = map.get(personName);
            if (person == null) {
                person = new PersonData(personName, 0L);
                map.put(personName, person);
            }

            person.addCounter(Long.valueOf(counter));
        }
    }

    class ProvinceTopData {
        private String provinceName;

        private PersonData mostRepeatedWoman;
        private PersonData leastRepeatedWoman;

        private PersonData mostRepeatedMan;
        private PersonData leastRepeatedMan;

        public ProvinceTopData(ProvinceData data) {
            this.provinceName = data.getName();

            List<PersonData> womenList = data.getWomen().values().stream().sorted(Comparator.comparing(PersonData::getCounter)).toList();
            mostRepeatedWoman = womenList.get(womenList.size() - 1);
            leastRepeatedWoman = womenList.get(0);

            List<PersonData> menList = data.getMen().values().stream().sorted(Comparator.comparing(PersonData::getCounter)).toList();
            mostRepeatedMan = menList.get(menList.size() - 1);
            leastRepeatedMan = menList.get(0);

        }

        public String getProvinceName() {
            return provinceName;
        }

        public PersonData getMostRepeatedWoman() {
            return mostRepeatedWoman;
        }

        public PersonData getLeastRepeatedWoman() {
            return leastRepeatedWoman;
        }

        public PersonData getMostRepeatedMan() {
            return mostRepeatedMan;
        }

        public PersonData getLeastRepeatedMan() {
            return leastRepeatedMan;
        }

    }
}