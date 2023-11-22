package cz.cvut.fit.si1.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {
    /**
     * Main method to run the spring server application.
     *
     * @param args captures cmd line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}
