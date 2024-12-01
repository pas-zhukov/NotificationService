package ru.pas_zhukov.notificationservice.entity;

import jakarta.persistence.*;
import ru.pas_zhukov.notificationservice.kafka.MessageType;


@Entity
@Table(name = "notifications")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_login", nullable = false)
    private String userLogin;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead;

    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Column(name = "changed_by_user_id", nullable = true)
    private Long changedByUserId;

    @Column(name = "message_type", nullable = false)
    private MessageType messageType;

    @Column(name = "name_change")
    private String name;

    @Column(name = "max_places_change")
    private String maxPlaces;

    @Column(name = "date_change")
    private String date;

    @Column(name = "cost_change")
    private String cost;

    @Column(name = "duration_change")
    private String duration;

    @Column(name = "location_id_change")
    private String locationId;

    @Column(name = "status_change")
    private String status;

    public NotificationEntity() {
    }

    public NotificationEntity(Long changedByUserId, String cost, String date, String duration, Long eventId, Long id, Boolean isRead, String locationId, String maxPlaces, MessageType messageType, String name, Long ownerId, String status, String userLogin) {
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
