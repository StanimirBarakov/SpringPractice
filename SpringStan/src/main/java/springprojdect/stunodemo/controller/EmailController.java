package springprojdect.stunodemo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springprojdect.stunodemo.util.MailUtil;


import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@RestController
public class EmailController {

    @PostMapping(value = "/products/mail")
    private String sendmail() throws AddressException, MessagingException, IOException {
        MailUtil.sendMail("sbarakov22@gmail.com","cw3tkaa@gmail.com","This is the subject","This is the content");
        return "mail sent";
    }
}
