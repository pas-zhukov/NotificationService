package ru.pas_zhukov.notificationservice.converter;

import org.springframework.stereotype.Component;
import ru.pas_zhukov.notificationservice.dto.NotificationResponseDto;
import ru.pas_zhukov.notificationservice.entity.NotificationEntity;
import ru.pas_zhukov.notificationservice.model.Notification;

@Component
public class NotificationConverter {
    public NotificationResponseDto toResponseDto(Notification notification) {
        return new NotificationResponseDto(
                notification.getChangedByUserId(),
                notification.getCost(),
                notification.getDate(),
                notification.getDuration(),
                notification.getEventId(),
                notification.getId(),
                notification.getRead(),
                notification.getLocationId(),
                notification.getMaxPlaces(),
                notification.getMessageType(),
                notification.getName(),
                notification.getOwnerId(),
                notification.getStatus(),
                notification.getUserLogin()
        );
    }

    public Notification toDomain(NotificationEntity notificationEntity) {
        return new Notification(
                notificationEntity.getChangedByUserId(),
                notificationEntity.getCost(),
                notificationEntity.getDate(),
                notificationEntity.getDuration(),
                notificationEntity.getEventId(),
                notificationEntity.getId(),
                notificationEntity.getRead(),
                notificationEntity.getLocationId(),
                notificationEntity.getMaxPlaces(),
                notificationEntity.getMessageType(),
                notificationEntity.getName(),
                notificationEntity.getOwnerId(),
                notificationEntity.getStatus(),
                notificationEntity.getUserLogin()
        );
    }
}
