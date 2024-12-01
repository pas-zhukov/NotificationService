package ru.pas_zhukov.notificationservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.pas_zhukov.notificationservice.kafka.MessageType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationResponseDto {
    private Long id;
    private String userLogin;
    private Boolean isRead;
    private Long eventId;
    private Long ownerId;
    private Long changedByUserId;
    private MessageType messageType;
    private String name;
    private String maxPlaces;
    private String date;
    private String cost;
    private String duration;
    private String locationId;
    private String status;

    public NotificationResponseDto() {
    }

    public NotificationResponseDto(Long changedByUserId, String cost, String date, String duration, Long eventId, Long id, Boolean isRead, String locationId, String maxPlaces, MessageType messageType, String name, Long ownerId, String status, String userLogin) {
        this.changedByUserId = changedByUserId;
        this.cost = cost;
        this.date = date;
        this.duration = duration;
        this.eventId = eventId;
        this.id = id;
        this.isRead = isRead;
        this.locationId = locationId;
        this.maxPlaces = maxPlaces;
        this.messageType = messageType;
        this.name = name;
        this.ownerId = ownerId;
        this.status = status;
        this.userLogin = userLogin;
    }

    public Long getChangedByUserId() {
        return changedByUserId;
    }

    public void setChangedByUserId(Long changedByUserId) {
        this.changedByUserId = changedByUserId;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getMaxPlaces() {
        return maxPlaces;
    }

    public void setMaxPlaces(String maxPlaces) {
        this.maxPlaces = maxPlaces;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
