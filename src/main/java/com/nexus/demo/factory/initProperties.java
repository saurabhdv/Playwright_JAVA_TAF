package com.nexus.demo.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class initProperties {


Properties prop;
    public Properties init_Property() {

        try {
            FileInputStream ip = new FileInputStream("src/main/resources/config.properties");
            prop = new Properties();
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
