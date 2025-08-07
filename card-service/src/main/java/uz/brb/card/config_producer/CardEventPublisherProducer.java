package uz.brb.card.config_producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import uz.brb.card.dto.CardDto;

@Service
@RequiredArgsConstructor
public class CardEventPublisherProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendCardCreateEvent(CardDto cardDto) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfigProducer.EXCHANGE,
                RabbitMQConfigProducer.ROUTING_KEY,
                cardDto
        );
    }
}
