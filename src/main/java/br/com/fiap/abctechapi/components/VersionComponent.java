package br.com.fiap.abctechapi.components;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class VersionComponent {

    private Properties properties;

    protected void setProperties() throws IOException {

        properties = new Properties();
        InputStream iStream = getClass().getClassLoader().getResourceAsStream("application.yml");

        properties.load(iStream);

    }

    public VersionComponent() {
        try {

            setProperties();

        } catch (IOException error) {

            System.out.println(error.getMessage());

        }
    }

    public String getName() {
        return properties.getProperty("build.name");
    }

    public String getVersion() {
        return properties.getProperty("build.version");
    }

    public String getNameVersion() {
        return getName() + " - " + getVersion();
    }
}
