package c3.msmb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import c3.msmb.model.Notification;
import java.util.List;


public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUsernameUsername(String username);
    List<Notification> findByUsernameUsernameAndReaded(String username, Boolean readed);
}