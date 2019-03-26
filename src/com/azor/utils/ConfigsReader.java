package com.azor.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigsReader {
    public static ConfigsReader getInstance() {
        return ourInstance;
    }

    public Properties load(String fileName) throws IOException {

        try{
            inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            if (inputStream != null)
                properties.load(inputStream);
            else
                throw new FileNotFoundException(fileName);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            assert inputStream != null;
            inputStream.close();
        }

        return properties;
    }

    private ConfigsReader() {
            properties = new Properties();
    }

    private Properties properties;
    private InputStream inputStream;
    private static ConfigsReader ourInstance = new ConfigsReader();
}
