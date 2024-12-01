package ru.pas_zhukov.notificationservice.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.pas_zhukov.notificationservice.service.NotificationService;

@Component
public class NotificationsCleaner {

    private static final Logger log = LoggerFactory.getLogger(NotificationsCleaner.class);
    private final NotificationService notificationService;

    public NotificationsCleaner(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Scheduled(cron = "${notifications.remove.cron}")
    public void cleanOldNotifications() {
        log.info("Cleaning old notifications");
        notificationService.deleteOldNotifications();
    }
}
