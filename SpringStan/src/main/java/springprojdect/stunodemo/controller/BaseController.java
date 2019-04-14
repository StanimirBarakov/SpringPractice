package springprojdect.stunodemo.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springprojdect.stunodemo.model.pojos.ErrorMsg;
import springprojdect.stunodemo.model.pojos.User;
import springprojdect.stunodemo.util.exceptions.BaseException;
import springprojdect.stunodemo.util.exceptions.NotAdminException;
import springprojdect.stunodemo.util.exceptions.NotLoggedException;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@RestController
public abstract class BaseController {


    static Logger logger = Logger.getLogger(ProductController.class.getName());


    @ExceptionHandler({NotLoggedException.class,NotAdminException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorMsg handleNotLogged(Exception e){
        ErrorMsg msg = new ErrorMsg(e.getMessage(),HttpStatus.UNAUTHORIZED.value(), LocalDateTime.now());
        return msg;
    }

    
    @ExceptionHandler({BaseException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMsg handleMyException(Exception e){
        logger.error(e.getMessage());
        ErrorMsg msg = new ErrorMsg(e.getMessage(),HttpStatus.BAD_REQUEST.value(),LocalDateTime.now());
        return msg;

    }
    
    @ExceptionHandler({Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMsg handleOtherException(Exception e){
        ErrorMsg msg = new ErrorMsg(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(),LocalDateTime.now());
        return msg;

    }

    protected void validateLogin(HttpSession session) throws NotLoggedException{
        if(session.getAttribute("loggedUser")==null){
            throw new NotLoggedException();
        }
    }
    protected void validateLoginAdmin(HttpSession session) throws NotAdminException, NotLoggedException {
        if(session.getAttribute("loggedUser")==null){
            throw new NotLoggedException();
        }else {
            User logged = (User)(session.getAttribute("loggedUser"));
            if(!logged.isAdmin()){
                throw new NotAdminException();
            }
        }
    }

}
