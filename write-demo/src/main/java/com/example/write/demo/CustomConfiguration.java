package com.example.write.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "custom")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CustomConfiguration {

    private String url;
    private String path;
    private String username;
    private String pwd;

}
