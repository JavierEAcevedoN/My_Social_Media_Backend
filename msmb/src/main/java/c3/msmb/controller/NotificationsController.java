package c3.msmb.controller;

import org.springframework.web.bind.annotation.RestController;

import c3.msmb.model.Notification;
import c3.msmb.service.NotificationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("api/notifications")
public class NotificationsController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public List<Notification> getNotifications() {
        return notificationService.getNotifications();
    }

    @GetMapping("/{username}")
    public List<Notification> geNotificationsByUsername(@PathVariable(name = "username") String username) {
        return notificationService.getNotificationsByUsername(username);
    }

    @GetMapping("/{username}/{readed}")
    public List<Notification> geNotificationsByUsername(@PathVariable(name = "username") String username,
            @PathVariable(name = "readed") Boolean readed) {
        return notificationService.getNotificationsByUsernameAndReaded(username, readed);
    }

    @PatchMapping("/{id}")
    public void markAsRead(@PathVariable(name = "id") Long id) {
        notificationService.markAsReaded(id);
    }
}