package ru.pas_zhukov.notificationservice.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pas_zhukov.notificationservice.converter.NotificationConverter;
import ru.pas_zhukov.notificationservice.dto.NotificationResponseDto;
import ru.pas_zhukov.notificationservice.dto.ReadNotificationsRequestDto;
import ru.pas_zhukov.notificationservice.model.Notification;
import ru.pas_zhukov.notificationservice.security.AuthenticationService;
import ru.pas_zhukov.notificationservice.service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final AuthenticationService authenticationService;
    private final NotificationConverter notificationConverter;

    public NotificationController(NotificationService notificationService, AuthenticationService authenticationService, NotificationConverter notificationConverter) {
        this.notificationService = notificationService;
        this.authenticationService = authenticationService;
        this.notificationConverter = notificationConverter;
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponseDto>> getCurrentUserNotifications() {
        String userLogin = authenticationService.getCurrentAuthenticatedUserOrThrow().getLogin();
        List<Notification> notifications = notificationService.getNotificationsByUserLogin(userLogin);
        return ResponseEntity.ok(notifications.stream().map(notificationConverter::toResponseDto).toList());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> setNotificationsRead(@Valid @RequestBody ReadNotificationsRequestDto readNotificationsRequestDto) {
        notificationService.markNotificationsAsRead(readNotificationsRequestDto.getNotificationIds());
        return ResponseEntity.noContent().build();
    }
}
