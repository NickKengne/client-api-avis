package codex.avisclient.controller;


import codex.avisclient.entity.User;
import codex.avisclient.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
   private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "register")
    public void register(@RequestBody User user){
        this.userService.register(user);
        log.info("Register");
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "activation-code")
    public void activation(@RequestBody Map<String, String> activation){
        this.userService.activation(activation);
    }
}
