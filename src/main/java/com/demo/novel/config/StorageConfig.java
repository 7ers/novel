package com.demo.novel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

@Configuration
public class StorageConfig {
    private String location = "/upload/";
    public String getLocation() {
        try{
            location = ResourceUtils.getURL("classpath:static").getPath()+"/upload/";
        }catch (Exception e){

        }
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}
