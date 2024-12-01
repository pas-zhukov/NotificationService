package ru.pas_zhukov.notificationservice.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.pas_zhukov.notificationservice.converter.NotificationConverter;
import ru.pas_zhukov.notificationservice.entity.*;
import ru.pas_zhukov.notificationservice.kafka.EventChangeMessage;
import ru.pas_zhukov.notificationservice.model.Notification;
import ru.pas_zhukov.notificationservice.repository.NotificationsRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NotificationService {

    public static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationsRepository notificationsRepository;
    private final NotificationConverter notificationConverter;
    private final ObjectMapper jacksonObjectMapper;

    public NotificationService(NotificationsRepository notificationsRepository, NotificationConverter notificationConverter, ObjectMapper jacksonObjectMapper) {
        this.notificationsRepository = notificationsRepository;
        this.notificationConverter = notificationConverter;
        this.jacksonObjectMapper = jacksonObjectMapper;
    }

    public List<Notification> getNotificationsByUserLogin(String userLogin) {
        List<NotificationEntity> userNotifications = notificationsRepository.findAllByUserLogin(userLogin);
        return userNotifications.stream().map(notificationConverter::toDomain).toList();
    }

    public void markNotificationsAsRead(List<Long> notificationIds) {
        notificationsRepository.markNotificationsAsRead(notificationIds);
    }

    public List<Notification> createNotifications(EventChangeMessage message) {
        List<NotificationEntity> notificationsToCreate = message.getUsers().stream().map(
                username -> {
                    try {
                        return new NotificationEntity(
                                message.getChangedByUserId(),
                                message.getCost() != null ? jacksonObjectMapper.writeValueAsString(message.getCost()) : null,
                                message.getDate() != null ? jacksonObjectMapper.writeValueAsString(message.getDate()) : null,
                                message.getDuration() != null ? jacksonObjectMapper.writeValueAsString(message.getDuration()) : null,
                                message.getEventId(),
                                null,
                                false,
                                message.getLocationId() != null ? jacksonObjectMapper.writeValueAsString(message.getLocationId()) : null,
                                message.getMaxPlaces() != null ? jacksonObjectMapper.writeValueAsString(message.getMaxPlaces()) : null,
                                message.getMessageType(),
                                message.getName() != null ? jacksonObjectMapper.writeValueAsString(message.getName()) : null,
                                message.getOwnerId(),
                                message.getStatus() != null ? jacksonObjectMapper.writeValueAsString(message.getStatus()) : null,
                                username,
                                Date.from(Instant.now())
                        );
                    } catch (JsonProcessingException e) {
                        logger.warn("Error creating notification", e);
                        return null;
                    }
                }
        ).toList();
        List<NotificationEntity> createdNotifications = notificationsRepository.saveAll(notificationsToCreate);
        logger.info("created notifications: {}", createdNotifications);
        return createdNotifications.stream().map(notificationConverter::toDomain).toList();
    }

    public void deleteOldNotifications() {
        // TODO: вынести "7" в .properties
        notificationsRepository.deleteAllByCreatedTimestampBefore(Date.from(LocalDateTime.now().minusDays(7).toInstant(ZoneOffset.UTC)));
    }
}
