package codex.avisclient.service;


import codex.avisclient.entity.Role;
import codex.avisclient.entity.User;
import codex.avisclient.entity.Validation;
import codex.avisclient.repository.UserRepository;
import codex.avisclient.repository.ValidationRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private ValidationService validationService;
    private ValidationRepository validationRepository;
    public void register(User user) {
        String hashpassword = this.passwordEncoder.encode(user.getPassword());
        if (!user.getEmail().contains("@") || !user.getEmail().contains(".")){
            throw new RuntimeException("your email is invalid");
        }

        Optional<User> userOptional = this.userRepository.findByEmail(user.getEmail());

        if (userOptional.isPresent()){
            throw new RuntimeException("This email is already exist");
        }

        Role roleUser = new Role();
        roleUser.setRole(Role.TypeRole.USER);
        user.setPassword(hashpassword);
        user.setRole(roleUser);
        User currentUser = this.userRepository.save(user);
        this.validationService.registerValidation(currentUser);

    }


    public void activation(Map<String, String> activation) {
        Validation validation =  this.validationService.readCode(activation.get("current_code"));
      if (Instant.now().isAfter(validation.getExpired_at())){
          throw new RuntimeException("Votre code a expire !");
      }
      User activeUser = this.userRepository.findById(validation.getUser().getId()).orElseThrow(() -> new RuntimeException("User unknown"));
      activeUser.setActive(true);
      validation.setActivated_at(Instant.now());
      this.validationRepository.save(validation);
      this.userRepository.save(activeUser);
    }
}
