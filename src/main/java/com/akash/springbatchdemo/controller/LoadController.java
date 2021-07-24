package com.akash.springbatchdemo.controller;

import lombok.extern.java.Log;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@Log
public class LoadController {
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @GetMapping("/load")
    public BatchStatus load() {
        JobExecution jobExecution = null;
        try {
            log.info("Entered LoadController load()");
            Map<String, JobParameter> maps = new HashMap<>();
            maps.put("time", new JobParameter(System.currentTimeMillis()));
            JobParameters jobParameters = new JobParameters(maps);
            jobExecution = jobLauncher.run(job, jobParameters);
            log.info("Batch job is running");
        } catch (Exception e) {
            log.warning(Arrays.toString(e.getStackTrace()));
        } finally {
            log.info("closing batch processes");
        }
        assert jobExecution != null;
        return jobExecution.getStatus();
    }

}
