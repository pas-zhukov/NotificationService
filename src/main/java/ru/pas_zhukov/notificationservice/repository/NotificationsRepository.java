package ru.pas_zhukov.notificationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.pas_zhukov.notificationservice.entity.NotificationEntity;

import java.util.List;

@Repository
public interface NotificationsRepository extends JpaRepository<NotificationEntity, Long> {
    @Query("SELECT n FROM NotificationEntity n")
    List<NotificationEntity> findAllByUserLogin(String login);

    @Query("UPDATE NotificationEntity n SET n.id = n.id")
    void markNotificationsAsRead(List<Long> notificationIds);
}
