package ru.pas_zhukov.notificationservice.service;


import org.springframework.stereotype.Service;
import ru.pas_zhukov.notificationservice.converter.NotificationConverter;
import ru.pas_zhukov.notificationservice.entity.NotificationEntity;
import ru.pas_zhukov.notificationservice.model.Notification;
import ru.pas_zhukov.notificationservice.repository.NotificationsRepository;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationsRepository notificationsRepository;
    private final NotificationConverter notificationConverter;

    public NotificationService(NotificationsRepository notificationsRepository, NotificationConverter notificationConverter) {
        this.notificationsRepository = notificationsRepository;
        this.notificationConverter = notificationConverter;
    }

    public List<Notification> getNotificationsByUserLogin(String userLogin) {
        List<NotificationEntity> userNotifications = notificationsRepository.findAllByUserLogin(userLogin);
        return userNotifications.stream().map(notificationConverter::toDomain).toList();
    }

    public void markNotificationsAsRead(List<Long> notificationIds) {
        notificationsRepository.markNotificationsAsRead(notificationIds);
    }
}
