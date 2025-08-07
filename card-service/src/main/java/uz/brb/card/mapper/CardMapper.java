package uz.brb.card.mapper;

import org.springframework.stereotype.Component;
import uz.brb.card.dto.CardDto;
import uz.brb.card.entity.Card;

import java.util.ArrayList;
import java.util.List;

@Component
public class CardMapper {
    public Card toEntity(CardDto cardDto) {
        return Card.builder()
                .name(cardDto.getName())
                .number(cardDto.getNumber())
                .build();
    }

    public CardDto toDto(Card card) {
        return CardDto.builder()
//                .id(card.getId())
                .userId(card.getUserId() != null ? card.getUserId() : null)
                .name(card.getName())
                .number(card.getNumber())
                .build();
    }

    public List<CardDto> dtoList(List<Card> list) {
        if (list != null && !list.isEmpty()) {
            return list.stream().map(this::toDto).toList();
        }
        return new ArrayList<>();
    }
}
