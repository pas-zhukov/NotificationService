package ru.pas_zhukov.notificationservice.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.pas_zhukov.notificationservice.service.NotificationService;

@Component
public class NotificationsCleaner {

    private final NotificationService notificationService;

    public NotificationsCleaner(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Scheduled(cron = "${notifications.remove.cron}")
    public void cleanOldNotifications() {
        notificationService.deleteOldNotifications();
    }
}
