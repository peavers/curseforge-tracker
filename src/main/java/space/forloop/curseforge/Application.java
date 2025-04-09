package space.forloop.curseforge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main application class for the CurseForge Tracker.
 *
 * This application periodically fetches download statistics for CurseForge projects
 * and stores them in InfluxDB for monitoring and analytics.
 *
 * @EnableScheduling - Enables Spring's scheduled task execution
 * @SpringBootApplication - Marks this as a Spring Boot application
 * @ConfigurationPropertiesScan - Automatically scans for @ConfigurationProperties classes
 */
@EnableScheduling
@SpringBootApplication
@ConfigurationPropertiesScan
public class Application {

    /**
     * Main method that starts the Spring Boot application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
