package cat.tecnocampus.webclassexample;

import cat.tecnocampus.webclassexample.repositories.NoteLabDAO;
import domain.NoteLab;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class WebclassexampleApplication {


    public static void main(String[] args) {
        SpringApplication.run(WebclassexampleApplication.class, args);
    }

}
