package codex.avisclient.service;


import codex.avisclient.entity.User;
import codex.avisclient.entity.Validation;
import codex.avisclient.repository.UserRepository;
import codex.avisclient.repository.ValidationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@AllArgsConstructor
@Service
public class ValidationService {
    private ValidationRepository  validationRepository;
    private NotificationService notificationService;
    private UserRepository userRepository;

    public void registerValidation (User user){
        Validation validation = new Validation();
        validation.setUser(user);
        //persist a created and expired_at time for validation
        Instant created_at = Instant.now();
        Instant expired_at = created_at.plus(2, ChronoUnit.MINUTES);
        validation.setExpired_at(expired_at);
        validation.setCreated_at(created_at);

        //create a new random code
        Random random = new Random();
        int randomCode = random.nextInt(999999);
        String code = String.format("%06d", randomCode);
        validation.setCode(code);
        this.validationRepository.save(validation);
        this.notificationService.send(validation);

    }

    public Validation readCode (String code){
     return this.validationRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Votre code est invalide"));
    }
}
