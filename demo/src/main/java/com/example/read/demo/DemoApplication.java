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

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	private static Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	@EventListener(ApplicationReadyEvent.class)
	public void read() throws IOException {
		Path safeArgs = Paths.get("/dev/shm/safeArgs");
		// Read from a Pipe
       if(Files.exists(safeArgs)){
           File inPipe = new File("/dev/shm/","safeArgs");
           try (FileInputStream inputStream = new FileInputStream(inPipe)) {
               StringBuilder sb = new StringBuilder();
               for(int i = inputStream.read(); i > 0; i = inputStream.read()){
                   char c = (char)i;
                   if(c == '\n' || c == '\r'){
                       break;
                   }else{
                       sb.append(c);
                   }
               }
               logger.info(" inputStream.read() output is :" +sb.toString());
           }
       }
	}
}
