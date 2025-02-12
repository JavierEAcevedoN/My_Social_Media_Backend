package c3.msmb.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"like\"")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate liked;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username", nullable = false)
    private User username;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_publication", nullable = false)
    private Publication idPublication;

    public Like() {}

    public Like(LocalDate liked, User username, Publication idPublication) {
        this.liked = liked;
        this.username = username;
        this.idPublication = idPublication;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLiked() {
        return liked;
    }

    public void setLiked(LocalDate liked) {
        this.liked = liked;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    public Publication getIdPublication() {
        return idPublication;
    }

    public void setIdPublication(Publication idPublication) {
        this.idPublication = idPublication;
    }

    @Override
    public String toString() {
        return "Like [id=" + id + ", liked=" + liked + ", username=" + username + ", idPublication=" + idPublication
                + "]";
    }
}