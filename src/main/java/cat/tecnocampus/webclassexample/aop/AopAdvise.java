package cat.tecnocampus.webclassexample.aop;


import domain.NoteLab;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class AopAdvise {
    private static final Logger logger = LoggerFactory.getLogger(AopAdvise.class);

    @Pointcut("execution( * cat.tecnocampus.webclassexample.repositories.NoteLabDAO.getNumNotes(..))")
    public void countNotesPointcut() {

    }

    @Before("countNotesPointcut()")
    public void beforeCountNotes() {
        logger.info("Abans de cridar count notes");
    }

    @After("countNotesPointcut()")
    public void afterCountNotes() {
        logger.info("Despres de cridar count notes");
    }

    @Pointcut("execution( * cat.tecnocampus.webclassexample.repositories.NoteLabDAO.getNoteById(int)) && args(id)")
    public void getNotesIdPointcut(int id) {

    }

    @Before("getNotesIdPointcut(id)")
    public void beforegetbyid(int id) {
        logger.info("Abans de cridar getNoteById: " + id);
    }

    @Pointcut("execution(* cat.tecnocampus.webclassexample.repositories.NoteLabDAO.saveNoteLab(domain.NoteLab)) " +
            "&& args(noteLab)")
    public void saveNotePointCut(NoteLab noteLab) {

    }

    @Around("saveNotePointCut(noteLab)")
    public int saveNoteAround(ProceedingJoinPoint pj, NoteLab noteLab) {
        try {
            logger.info("Abans de cridar save note");
            int n  = (int) pj.proceed(new Object[]{noteLab});
            logger.info("despres de cridar save note");
            return n;
        } catch (Throwable throwable) {
            return -1;
        }
    }

    @Pointcut("execution(* cat.tecnocampus.webclassexample.repositories.NoteLabDAO.saveNoteLabList(..))")
    public void saveNoteNoArgsPointCut() {

    }

    @Around("saveNoteNoArgsPointCut()")
    public int[] saveNoteAroundNoArgs(ProceedingJoinPoint pj) {
        try {
            logger.info("Abans cridar save note. No args");
            int[] n = (int[]) pj.proceed();
            logger.info("Despres cridar save note. No args");
            return n;
        } catch (Throwable throwable) {
            return new int[]{-1};
        }
    }
}
