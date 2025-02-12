package c3.msmb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import c3.msmb.model.User;
import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, String> {
        @Modifying
        @Transactional
        @Query("UPDATE User us SET " +
                "us.fullName = CASE WHEN :fullName IS NOT NULL THEN :fullName ELSE us.fullName END, " +
                "us.phone = CASE WHEN :phone IS NOT NULL THEN :phone ELSE us.phone END, " +
                "us.updated = CURRENT_TIMESTAMP, " +
                "us.biography = CASE WHEN :biography IS NOT NULL THEN :biography ELSE us.biography END, " +
                "us.profilePhoto = CASE WHEN :profilePhoto IS NOT NULL THEN :profilePhoto ELSE us.profilePhoto END " +
                "WHERE us.username = :username")
        int updatePartialUser(
                @Param("fullName") String fullName,
                @Param("phone") String phone,
                @Param("biography") String biography,
                @Param("profilePhoto") String profilePhoto,
                @Param("username") String username);
}