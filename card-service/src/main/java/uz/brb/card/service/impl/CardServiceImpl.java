package uz.brb.card.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.brb.card.config_producer.CardEventPublisherProducer;
import uz.brb.card.dto.CardDto;
import uz.brb.card.entity.Card;
import uz.brb.card.exception.ResourceNotFoundException;
import uz.brb.card.feign.UserClient;
import uz.brb.card.mapper.CardMapper;
import uz.brb.card.repository.CardRepository;
import uz.brb.card.service.CardService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final UserClient userClient;
    private final CardMapper cardMapper;
    private final CardEventPublisherProducer cardEventPublisherProducer;

    @Override
    public ResponseEntity<?> create(CardDto cardDto) {
        try {
            boolean response = userClient.existsById(cardDto.getUserId());

            if (!response) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Foydalanuvchi topilmadi: = " + cardDto.getUserId());
            }

            Card card = cardMapper.toEntity(cardDto);
            card.setUserId(cardDto.getUserId());
            card.setCreatedAt(LocalDateTime.now());

            // Kartani yaratish uchun xabar yuboramiz
            cardDto = new CardDto(cardDto.getUserId(), cardDto.getName(), cardDto.getNumber());
            cardEventPublisherProducer.sendCardCreateEvent(cardDto);

            System.out.println("cardDto = " + cardDto);
            return ResponseEntity.ok(cardRepository.save(card));
        } catch (FeignException.NotFound e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Foydalanuvchi topilmadi: ID = " + cardDto.getUserId());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Xatolik yuz berdi: " + ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        return ResponseEntity.ok(cardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found: " + id)));
    }

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(cardRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getByUserId(Long userId) {
        boolean response = userClient.existsById(userId);
        if (!response) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Foydalanuvchi topilmadi: = " + userId);
        }
        return ResponseEntity.ok(cardRepository.findAllByUserId(userId));
    }
}
