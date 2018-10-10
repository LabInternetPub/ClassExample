package cat.tecnocampus.webclassexample.webcontroller;

import cat.tecnocampus.webclassexample.repositories.NoteLabDAO;
import domain.NoteLab;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class WebControllerPost {
    NoteLabDAO noteLabDAO;

    public WebControllerPost(NoteLabDAO noteLabDAO) {
        this.noteLabDAO = noteLabDAO;
    }

    @GetMapping("newNote")
    public String newNote(Model model) {
        model.addAttribute("noteLab", new NoteLab());
        return "noteForm";
    }

    @PostMapping("newNote")
    public String createNote(@Valid NoteLab noteLab, Errors errors) {
        if (errors.hasErrors()) {
            return "noteForm";
        }

        noteLabDAO.saveNoteLab(noteLab);
        return "redirect:/getAllNotes";
    }

    @GetMapping("newNoteShowNote")
    public String newNoteShowNote(Model model) {
        model.addAttribute("noteLab", new NoteLab());
        return "noteForm";
    }

    @PostMapping("newNoteShowNote")
    public String createNoteShowNote(NoteLab noteLab, RedirectAttributes attributes) {
        noteLabDAO.saveNoteLab(noteLab);

        attributes.addFlashAttribute("note", noteLab);  //sends via Model
        attributes.addAttribute("title", noteLab.getTitle());
        return "redirect:/getNote";
    }

    @GetMapping("getNote")
    public String getNote(Model model) {

        return "getNote";
    }


}
