package com.bingo.lottoservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("app")
public class AppConfiguration {
    private String title;
    private String ftplink;

    public String getFtplink() {
        return ftplink;
    }

    public void setFtplink(String ftplink) {
        this.ftplink = ftplink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
