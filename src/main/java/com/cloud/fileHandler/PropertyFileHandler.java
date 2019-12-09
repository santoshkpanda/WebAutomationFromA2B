package com.cloud.fileHandler;

import com.cloud.core.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileHandler {

        /**
         * This file will read the config.properly file and return the value of key
         * @param propertyKey
         * @return
         * @throws IOException
         */
        public static String getPropertyValue(String propertyKey) throws IOException
        {
            String propertyValue = null;
            try{
                Properties CurrProp = new Properties();
                FileInputStream fileRead = new FileInputStream("./config/Config.property");
                CurrProp.load(fileRead);
                propertyValue = CurrProp.getProperty(propertyKey);
                fileRead.close();
                return propertyValue;
            }
            catch(Exception E){
                Log.error("Error in reading property file");
                Log.error(E.toString());
                System.out.println("Error in reading property file"+ E.toString());
            }
            return propertyValue;
        }
    }
