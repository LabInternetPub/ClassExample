package cat.tecnocampus.webclassexample.apicontroller;

import cat.tecnocampus.webclassexample.repositories.NoteLabDAO;
import domain.NoteLab;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class ApiController {
    private NoteLabDAO noteLabDAO;

    public ApiController(NoteLabDAO noteLabDAO) {
        this.noteLabDAO = noteLabDAO;
    }

    @GetMapping(value = "getNote/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteLab getNote(@PathVariable int id) {
        return noteLabDAO.getNoteById(id);
    }

    @GetMapping(value = "getAllNotes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteLab> getAllNotes() {
        return noteLabDAO.getAllNotes();
    }

    @PostMapping(value = "newNote", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteLab createNote(@RequestBody NoteLab noteLab) {
        noteLabDAO.saveNoteLab(noteLab);
        return noteLab;
    }
}
