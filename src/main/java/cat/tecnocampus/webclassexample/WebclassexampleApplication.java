package cat.tecnocampus.webclassexample;

import cat.tecnocampus.webclassexample.repositories.NoteLabDAO;
import domain.NoteLab;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class WebclassexampleApplication implements CommandLineRunner {

    NoteLabDAO noteLabDAO;

    public WebclassexampleApplication(NoteLabDAO noteLabDAO) {
        this.noteLabDAO = noteLabDAO;
    }

    public static void main(String[] args) {
        SpringApplication.run(WebclassexampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<NoteLab> noteLabList = new ArrayList<>();
        noteLabList.add(new NoteLab("Hola", "la primera a la llista"));
        noteLabList.add(new NoteLab("Adeu", "La segona a la llista"));
        noteLabDAO.saveNoteLabList(noteLabList);
    }
}
