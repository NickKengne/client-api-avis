package codex.avisclient.service;

import codex.avisclient.entity.Validation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
@Setter

public class NotificationService {
   JavaMailSender javaMailSender;
    public void send(Validation validation){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-reply@nickdk.codex");
        message.setTo(validation.getUser().getEmail());
        message.setSubject("Code d'activation sur CODEX");


        String contentMessage = String.format(
                "Bonjour %s,<br/> votre code d'activation est %s <br/>ce " +
                        "code expirera dans les 2 minutes A " +
                        "bientotvcvcvvcvcvcvcv" ,
                validation.getUser().getUsername(),
                validation.getCode()
        );
        message.setText(contentMessage);
        javaMailSender.send(message);
    }
}
