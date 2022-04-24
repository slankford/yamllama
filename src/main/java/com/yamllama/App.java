package com.yamllama;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.yamllama.model.JobsEntry;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
        // get files ([0] needs to be the mcmmo file, [1] needs to be the jobs file)
        System.out.println("args0: " + args[0]);
        String mcmmoFileName = args[0] + ".yaml";
        System.out.println("args1: " + args[1]);
        String jobsFileName = args[1] + ".yaml";
        System.out.println("args2: " + args[2]);
        BigDecimal jobsValue = new BigDecimal(args[2]);

        // Loading the MCMMO YAML file from the /resources folder and convert to Java
        // object
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File mcmmoFile = new File(classLoader.getResource(mcmmoFileName).getFile());
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        Map<String, Integer> mcMMOEntries = objectMapper.readValue(mcmmoFile, Map.class);

        // Loading the JOBS YAML file from the /resources folder and convert to Java
        // object
        File jobsFile = new File(classLoader.getResource(jobsFileName).getFile());
        Map<String, JobsEntry> jobsEntries = objectMapper.readValue(jobsFile, Map.class);

        // Find values that are in mcMMOEntries, but missing from jobs
        Map<String, Integer> missingJobsEntriesFromMCMMO = mcMMOEntries.entrySet().stream()
                .filter(mcMMOEntry -> !jobsEntries.entrySet().stream()
                        .anyMatch(jobsEntry -> mcMMOEntry.getKey().toUpperCase()
                                .equals(jobsEntry.getKey().toUpperCase())))
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));

        Map<String, JobsEntry> newJobsEntries = new HashMap<>();
        missingJobsEntriesFromMCMMO.forEach((k, v) -> {
            newJobsEntries.put(k.toLowerCase(),
                    new JobsEntry(jobsValue, jobsValue, jobsValue));
        });

        // Write missing values that were found and new jobs values
        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        om.writeValue(new File(args[0] + "_missingValues_output.yaml"), missingJobsEntriesFromMCMMO);
        om.writeValue(new File(args[1] + "_new_jobs_output.yaml"), newJobsEntries);
    }
}
