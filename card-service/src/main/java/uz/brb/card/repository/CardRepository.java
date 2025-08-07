package uz.brb.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.brb.card.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
}