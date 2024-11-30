package ru.pas_zhukov.notificationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pas_zhukov.notificationservice.entity.NotificationEntity;

import java.util.List;

@Repository
public interface NotificationsRepository extends JpaRepository<NotificationEntity, Long> {
    List<NotificationEntity> findAllByUserLogin(String login);
    void markNotificationsAsRead(List<Long> notificationIds);
}
