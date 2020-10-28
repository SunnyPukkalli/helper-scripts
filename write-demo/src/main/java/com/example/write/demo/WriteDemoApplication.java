package com.example.write.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class WriteDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WriteDemoApplication.class, args);

	}

	private static Logger logger = LoggerFactory.getLogger(WriteDemoApplication.class);

	@Autowired
	private CustomConfiguration customConfiguration;

	@EventListener(ApplicationReadyEvent.class)
	public void write() throws IOException {

		Path safeArgs = Paths.get("/dev/shm/safeArgs");

		if(!Files.exists(safeArgs)){
			logger.info("File does not existing , so creating it");
			Process proc = Runtime.getRuntime().exec(
					new String[] {"/bin/bash", "-c", "mkfifo /dev/shm/safeArgs"}
			);
		}
		// Write to a Pipe.
		File outPipe = new File("/dev/shm/","safeArgs");

		Thread write = new Thread(new Runnable() {
			@Override
			public void run() {
				try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new DataOutputStream(new FileOutputStream(outPipe))))) {
					bw.write("URL:"+customConfiguration.getUrl()+"\n");
					bw.write("PATH:"+customConfiguration.getPath()+"\n");
					bw.write("USERNAME:"+customConfiguration.getUsername()+"\n");
					bw.write("PASSWORD:"+customConfiguration.getPwd()+"\n");
					bw.flush();
				} catch (IOException e) {
					throw new IllegalStateException(e);
				}
			}
		});
		write.start();
	}

}
