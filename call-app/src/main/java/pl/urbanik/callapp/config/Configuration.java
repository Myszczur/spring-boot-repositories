package pl.urbanik.callapp.config;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.*;
import pl.urbanik.callapp.CallAppApplication;
import pl.urbanik.callapp.exception.ApplicationException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.Properties;

/**
 * @author kurbanik
 */

@Log4j
public class Configuration {

    @Getter
    private static final String appName = setAppName();

    static {
        try {
            getConfig();
        } catch (ApplicationException e) {
            log.error("Configuration error ......." + e.getLocalizedMessage());
        }
    }

    private static void getConfig() throws ApplicationException {

        try {

            DailyRollingFileAppender appender = new DailyRollingFileAppender();
            appender.setThreshold(Level.INFO);
            appender.setLayout(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n"));
            appender.setDatePattern("'.'yyyy-MM-dd");
            appender.setImmediateFlush(true);
            appender.setAppend(true);
            appender.setFile(getCurrentPath() + "log\\Log.log");
            appender.activateOptions();
            Logger.getRootLogger().addAppender(appender);

        } catch (
                Exception e) {
            throw new ApplicationException("Configuration error" + e.getLocalizedMessage());
        }
    }

    private static String getCurrentPath() {
        try {
            CodeSource codeSource = CallAppApplication.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource
                    .getLocation()
                    .toURI()
                    .getPath());
            return jarFile
                    .getParentFile()
                    .getPath() + "\\";
        } catch (URISyntaxException e) {
            System.exit(0);
            return "";
        }
    }

    private static String setAppName() {
        try {
            Properties properties = new Properties();
            properties.load(Configuration.class.getResourceAsStream("/pom.properties"));
            return properties.getProperty("artifactId");
        } catch (IOException ex) {
            log.error("Configuration error setAppName:" + ex.getMessage());
            return "no-application-name-set";
        }
    }
}
