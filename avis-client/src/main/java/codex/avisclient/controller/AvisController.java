package codex.avisclient.controller;

import codex.avisclient.entity.Avis;
import codex.avisclient.service.AvisService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("")
public class AvisController {
    private final AvisService avisService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("create")
    public void create (@RequestBody Avis avis){
        this.avisService.create(avis);
    }
}
