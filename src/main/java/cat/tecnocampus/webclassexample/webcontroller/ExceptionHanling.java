package cat.tecnocampus.webclassexample.webcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHanling {

    private final Logger logger = LoggerFactory.getLogger(ExceptionHanling.class);

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String handleUsernameDoesNotExist(Model model, HttpServletRequest request, Exception ex) {
        String url = request.getRequestURL().toString();

        logger.error("Request: " + url + " raised " + ex);

        model.addAttribute("id", url.substring(url.lastIndexOf("/") + 1));
        return "error/noteIdDoesNotExist";
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatch(Model model, HttpServletRequest request, Exception ex) {
        String url = request.getRequestURL().toString();

        logger.error("Request: " + url + " raised " + ex.getMessage());

        int beginIdx = ex.getMessage().indexOf("required type");
        int endIdx = ex.getMessage().indexOf(";", beginIdx);

        model.addAttribute("arg", url.substring(url.lastIndexOf("/") + 1));
        model.addAttribute("type", ex.getMessage().substring(beginIdx + "required type".length(), endIdx));

        return "error/MethodArgumentTypeMismatch";
    }

}
