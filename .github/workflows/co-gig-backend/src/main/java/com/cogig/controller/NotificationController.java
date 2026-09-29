package com.cogig.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cogig.model.Notification;
import com.cogig.repository.NotificationRepository;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    private final NotificationRepository notificationRepository;

    public NotificationController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<Notification> getUserNotifications(
            @PathVariable Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    @GetMapping("/user/{userId}/unread")
    public List<Notification> getUnreadNotifications(
            @PathVariable Long userId) {
        return notificationRepository.findByUserIdAndIsReadFalse(userId);
    }

    @PostMapping
    public Notification createNotification(
            @RequestBody Notification notification) {
        return notificationRepository.save(notification);
    }

    @PatchMapping("/{id}/read")
    public Notification markAsRead(@PathVariable Long id) {

        Notification notification =
                notificationRepository.findById(id).orElse(null);

        if (notification == null) {
            return null;
        }

        notification.setRead(true);

        return notificationRepository.save(notification);
    }
}