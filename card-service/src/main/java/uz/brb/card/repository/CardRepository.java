package uz.brb.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.brb.card.entity.Card;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByUserId(Long userId);
}