package codex.avisclient.service;


import codex.avisclient.entity.Avis;
import codex.avisclient.repository.AvisRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class AvisService {
    private final AvisRepository avisRepository;
    public void create(Avis avis) {
        this.avisRepository.save(avis);
    }
}
