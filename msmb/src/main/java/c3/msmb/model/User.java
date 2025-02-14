package c3.msmb.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"user\"")
public class User implements UserDetails{
    @Id
    @Column(length = 128, nullable = false)
    private String username;

    @Column(length = 256, nullable = false)
    private String email;

    @Column(length = 512, nullable = false)
    private String fullName;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String password;

    @Column(length = 16, nullable = false)
    private String phone;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private LocalDateTime created;

    private LocalDateTime updated;

    @Column(columnDefinition = "TEXT")
    private String biography;

    @Column(columnDefinition = "TEXT")
    private String profilePhoto;

    public User() {}

    public User(String username, String email, String fullName, String password, String phone, LocalDate birthDate,
            LocalDateTime created) {
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.phone = phone;
        this.birthDate = birthDate;
        this.created = created;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", email=" + email + ", fullName=" + fullName + ", password=" + password
                + ", phone=" + phone + ", birthDate=" + birthDate + ", created=" + created + ", updated=" + updated
                + ", biography=" + biography + ", profilePhoto=" + profilePhoto + "]";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}