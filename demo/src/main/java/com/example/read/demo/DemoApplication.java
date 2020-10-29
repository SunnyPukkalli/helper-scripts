package com.example.read.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class DemoApplication {

    private static final String SAFE_PATH ="/dev/shm/securityAssets/safe/";
    private static final String URL=SAFE_PATH+"url";
    private static final String BASE_PATH=SAFE_PATH+"basePath";
    private static final String USERNAME=SAFE_PATH+"userName";
    private static final String PWD=SAFE_PATH+"pwd";

    CustomArgument args;


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	private static Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	@EventListener(ApplicationReadyEvent.class)
	public void read() throws IOException {

        readValues();
        logger.info("SAFE-000002 : Starting Safe Bootstrapping...");

        // Setting App code

        logger.info("SAFE-000003 : Safe Bootstrapping completed");


        logger.info("SAFE-000004 : Now Deleting all files in "+SAFE_PATH);
        Files.walk(Paths.get(SAFE_PATH))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .forEach(File::delete);

        logger.info("SAFE-000005 : All Files Deleted.. ");

	}

    private void readValues() throws IOException {
        if(!Files.exists(Paths.get(SAFE_PATH))){
            logger.error("SAFE-ERROR-1 : "+SAFE_PATH+" does not exist");
            throw new RuntimeException(SAFE_PATH+" Path does not exist");
        }else{
            if( !Files.exists(Paths.get(URL)) || !Files.exists(Paths.get(BASE_PATH)) ||
                    !Files.exists(Paths.get(USERNAME)) || !Files.exists(Paths.get(PWD)) ){
                logger.error("SAFE-ERROR-2 : All the required Files are not present in "+SAFE_PATH);
                throw new RuntimeException("All the required Files are not present in "+SAFE_PATH);
            } else {
                args.setUrl(new String(Files.readAllBytes(Paths.get(URL))).trim());
                args.setBasePath(new String(Files.readAllBytes(Paths.get(BASE_PATH))).trim());
                args.setUserName(new String(Files.readAllBytes(Paths.get(USERNAME))).trim());
                args.setPwd(new String(Files.readAllBytes(Paths.get(PWD))).trim());
                args.setEnvName(System.getProperty("ENV_NAME").trim());


                logger.info("SAFE-000001 : All properties are read .." +args.toString());
            }
    }

}

    public DemoApplication() {
        args = new CustomArgument();

    }


}
