package ru.pas_zhukov.notificationservice.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.pas_zhukov.notificationservice.entity.NotificationEntity;

import java.util.Date;
import java.util.List;

@Repository
public interface NotificationsRepository extends JpaRepository<NotificationEntity, Long> {
    @Query("SELECT n FROM NotificationEntity n WHERE n.userLogin = :login AND n.isRead = false")
    List<NotificationEntity> findAllByUserLogin(@Param("login") String login);

    @Query("UPDATE NotificationEntity n SET n.isRead = true WHERE n.id IN :notificationIds")
    @Modifying
    @Transactional
    void markNotificationsAsRead(@Param("notificationIds") List<Long> notificationIds);

    @Modifying
    @Transactional
    void deleteAllByCreatedTimestampBefore(@Param("date") Date date);
}
