package ru.pas_zhukov.notificationservice.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ReadNotificationsRequestDto {

    @NotNull
    private List<Long> notificationIds;

    public ReadNotificationsRequestDto() {
    }

    public ReadNotificationsRequestDto(List<Long> notificationIds) {
        this.notificationIds = notificationIds;
    }

    public @NotNull List<Long> getNotificationIds() {
        return notificationIds;
    }

    public void setNotificationIds(@NotNull List<Long> notificationIds) {
        this.notificationIds = notificationIds;
    }
}
