package Properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHolder {
    private static Properties prop;
    private static PropertiesHolder propertiesHolder;

    public static PropertiesHolder getInstance() throws Exception {
        if(propertiesHolder == null) {
            propertiesHolder = new PropertiesHolder();
        }
        return propertiesHolder;
    }

    private PropertiesHolder() throws Exception {
        try  {
            // Loading the properties file
            InputStream input = new FileInputStream("src/test/resources/conf.properties");
            prop = new Properties();
            prop.load(input);

        } catch (IOException ex) {
            throw new Exception("Fail to load properties from conf file. " + ex.getMessage());
        }
    }

    public String getProperty(String propertyName) throws Exception {
        String property = prop.getProperty(propertyName);
        if(property == null) {
            throw new Exception("Property " + propertyName + " not found!");
        }
        return property;
    }

}
