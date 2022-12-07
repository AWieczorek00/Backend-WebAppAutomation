package pl.backendbscthesis.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.backendbscthesis.service.MailService;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/sendMail")
    public void sendMail() throws MessagingException {

        mailService.sendMail(
                "adam98523@gmail.com",
                "Wygrałeś",
                "<p style=\"text - align:center;\"><strong>Do twojego konta zostało dodane nowe zadanie.</strong></p>\n<p style=\" text - align:center;\"><strong>Sprawdzi je u siebie</strong></p>", true);


    }


}
