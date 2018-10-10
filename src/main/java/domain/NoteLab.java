package domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class NoteLab {

    @NotNull(message = "Title cannot be null")
    @Size(min = 4, max = 50, message = "Title must be between 4 an 50 characters long")
    @Pattern(regexp = "^[A-Z].+", message = "Title must begin with a capital letter")
    private String title;

    @NotNull(message = "Title cannot be null")
    @Size(min = 4, max = 5000, message = "Content must be between 4 an 500 characters long")
    @Pattern(regexp = "^[A-Z].+", message = "Content must begin with a capital letter")
    private String content;

    private LocalDateTime dateCreation;
    private LocalDateTime dateEdit;

    public NoteLab() {
        createDates();
    }

    public NoteLab(String title, String content) {
        this.title = title;
        this.content = content;

        createDates();
    }

    private void createDates() {
        LocalDateTime date = LocalDateTime.now();

        dateCreation = date;
        dateEdit = date;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public LocalDateTime getDateEdit() {
        return dateEdit;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setDateEdit(LocalDateTime dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String toString(){
        return "NoteLab: "+this.title+", Content: "+ this.content;
    }


}
