package info.schuwan.api2;

import info.schuwan.api2.controllers.SendMailController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Api2Application {

    public static void main(String[] args) {
//        new SendMailController();
        SpringApplication.run(Api2Application.class, args);
    }

}
