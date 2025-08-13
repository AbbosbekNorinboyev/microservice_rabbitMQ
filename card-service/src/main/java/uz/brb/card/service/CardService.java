package uz.brb.card.service;

import org.springframework.http.ResponseEntity;
import uz.brb.card.dto.CardDto;

public interface CardService {
    ResponseEntity<?> create(CardDto cardDto);

    ResponseEntity<?> get(Long id);

    ResponseEntity<?> getAll();

    ResponseEntity<?> getByUserId(Long userId);
}
