package uz.brb.user.config_consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import uz.brb.user.dto.CardDto;

@Service
@Slf4j
public class CardConsumer {

    @RabbitListener(queues = RabbitMQConfigConsumer.QUEUE)
    public void listen(CardDto cardDto) {
        log.info("Keldi: {}", cardDto);
        System.out.println("Keldi: " + cardDto);
        // Bu yerda kartani DB ga saqlash mumkin
        // cardService.create(cardDto);
    }
}
