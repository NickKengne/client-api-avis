package codex.avisclient.repository;

import codex.avisclient.entity.Validation;
import lombok.Getter;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface  ValidationRepository extends CrudRepository<Validation , Integer> {
     Optional<Validation> findByCode(String current_code);

}