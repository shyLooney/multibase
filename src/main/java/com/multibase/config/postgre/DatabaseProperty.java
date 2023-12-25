package com.multibase.config.postgre;

import lombok.Data;

@Data
public class DatabaseProperty {
    private String url;
    private String username;
    private String password;
    private String classDriver;
}