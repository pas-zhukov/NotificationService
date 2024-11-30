package ru.pas_zhukov.notificationservice.converter;

import org.springframework.stereotype.Component;
import ru.pas_zhukov.notificationservice.dto.NotificationResponseDto;
import ru.pas_zhukov.notificationservice.entity.NotificationEntity;
import ru.pas_zhukov.notificationservice.model.Notification;

@Component
public class NotificationConverter {
    public NotificationResponseDto toResponseDto(Notification notification) {
        return null;
    }

    public Notification toDomain(NotificationEntity notificationEntity) {
        return null;
    }
}
