package ru.pas_zhukov.notificationservice.kafka;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.pas_zhukov.notificationservice.service.NotificationService;

@Component
public class EventKafkaListener {

    private static final Logger log = LoggerFactory.getLogger(EventKafkaListener.class);
    private final NotificationService notificationService;

    public EventKafkaListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @KafkaListener(topics = "events-topic", containerFactory = "containerFactory")
    public void listenEvents(
            ConsumerRecord<Long, EventChangeMessage> message
    ) {
        log.info("Got event message: event={}", message.value());
        notificationService.createNotifications(message.value());
    }
}
