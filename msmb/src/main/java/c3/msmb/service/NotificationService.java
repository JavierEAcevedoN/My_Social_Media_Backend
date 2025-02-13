package c3.msmb.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import c3.msmb.exceptions.notification.ByUsernameAndReadedException;
import c3.msmb.exceptions.notification.ByUsernameException;
import c3.msmb.exceptions.notification.SaveNotificationException;
import c3.msmb.exceptions.notification.GetNotificationsException;
import c3.msmb.model.Notification;
import c3.msmb.model.User;
import c3.msmb.repository.NotificationRepository;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getNotificationsByUsername(String username) {
        List<Notification> notifications = notificationRepository.findByUsernameUsername(username);
        if (notifications.isEmpty()) {
            throw new ByUsernameException("Notifications not found for user " + username);
        }
        return notifications;
    }

    public List<Notification> getNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        if (notifications.isEmpty()) {
            throw new GetNotificationsException("Notifications not found");
        }
        return notifications;
    }

    public List<Notification> getNotificationsByUsernameAndReaded (String username, Boolean readed) {
        List<Notification> notifications = notificationRepository.findByUsernameUsernameAndReaded(username, readed);
        if (notifications.isEmpty()) {
            throw new ByUsernameAndReadedException("Notifications not found for user " + username);
        }
        return notifications;
    }

    public Notification saveNotification(String content, User user) {
        try {
            Notification notification = new Notification(content, LocalDateTime.now(), false, user);
            return notificationRepository.save(notification);
        } catch (Exception e) {
            throw new SaveNotificationException("User " + user.getFullName() + " not exist");
        }
    }
}