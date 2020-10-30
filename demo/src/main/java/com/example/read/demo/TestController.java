package com.example.read.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class TestController {

    private static final String SAFE_PATH ="/dev/shm/securityAssets/safe/";
    private static final String URL=SAFE_PATH+"url";
    private static final String BASE_PATH=SAFE_PATH+"basePath";
    private static final String USERNAME=SAFE_PATH+"userName";
    private static final String PWD=SAFE_PATH+"pwd";


    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/get")
    public String  get() throws IOException {
        CustomArgument args = new CustomArgument();

        boolean exists = Files.exists(Paths.get(SAFE_PATH));
        logger.info(" Checking if path exists or not : "+exists );
        return "Success";
    }

}
