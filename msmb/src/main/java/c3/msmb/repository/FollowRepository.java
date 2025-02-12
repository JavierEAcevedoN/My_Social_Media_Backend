package c3.msmb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import c3.msmb.model.Follow;
import jakarta.transaction.Transactional;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long>{
    List<Follow> findByFollowFromUsername(String followFrom);
    List<Follow> findByFollowToUsername(String followTo);

    @Modifying
    @Transactional
    @Query("INSERT INTO Follow (followFrom, followTo, followed) VALUES " +
        "((SELECT u FROM User u WHERE u.username = :followFrom), " +
        "(SELECT u FROM User u WHERE u.username = :followTo), " +
        "CURRENT_TIMESTAMP)")
    int follow(@Param("followFrom") String followFrom, @Param("followTo") String followTo);

    @Modifying
    @Transactional
    @Query("DELETE FROM Follow f WHERE f.followFrom.username = :followFrom AND f.followTo.username = :followTo")
    int unFollow(@Param("followFrom") String followFrom, @Param("followTo") String followTo);
}