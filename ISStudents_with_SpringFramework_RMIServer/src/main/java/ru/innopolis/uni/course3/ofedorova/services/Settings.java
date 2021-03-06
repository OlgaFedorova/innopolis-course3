package ru.innopolis.uni.course3.ofedorova.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Olga on 22.12.2016.
 */
public class Settings {

    private static final Settings INSTANCE = new Settings();

    private final Properties properties = new Properties();

    private Settings() {
        try {
            properties.load(new FileInputStream(this.getClass().getClassLoader().getResource("ofedorova.properties").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
