package c3.msmb.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import c3.msmb.model.Notification;
import c3.msmb.model.User;
import c3.msmb.repository.NotificationRepository;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getNotificationsByUsername(String username) {
        return notificationRepository.findByUsernameUsername(username);
    }

    public List<Notification> getNotifications() {
        return notificationRepository.findAll();
    }

    public List<Notification> getNotificationsByUsernameAndReaded (String username, Boolean readed) {
        return notificationRepository.findByUsernameUsernameAndReaded(username, readed);
    }

    public Notification saveNotification(String content, User user) {
        Notification notification = new Notification(content, LocalDateTime.now(), false, user);
        return notificationRepository.save(notification);
    }
}