package com.example.read.demo;

public class CustomArgument {


    private String url;

    private String basePath;

    private String userName;

    private String pwd;

    private String envName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    public CustomArgument(String url, String basePath, String userName, String pwd, String envName) {
        this.url = url;
        this.basePath = basePath;
        this.userName = userName;
        this.pwd = pwd;
        this.envName = envName;
    }

    public CustomArgument() {
    }
}
