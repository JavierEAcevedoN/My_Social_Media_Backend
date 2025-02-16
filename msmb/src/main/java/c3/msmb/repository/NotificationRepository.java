package c3.msmb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import c3.msmb.model.Notification;
import jakarta.transaction.Transactional;

import java.util.List;


public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUsernameUsername(String username);
    List<Notification> findByUsernameUsernameAndReaded(String username, Boolean readed);

    @Modifying
    @Transactional
    @Query("UPDATE Notification n SET n.readed = TRUE WHERE n.id = :id ")
    int markAsRead(@Param("id") Long id);
}