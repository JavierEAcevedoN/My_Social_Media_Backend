package c3.msmb.model;

import java.time.LocalDateTime;

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
@Table(name = "follow")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime followed;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "follow_from", nullable =  false)
    private User followFrom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "follow_to", nullable = false)
    private User followTo;

    public Follow() {}

    public Follow(LocalDateTime followed, User followFrom, User followTo) {
        this.followed = followed;
        this.followFrom = followFrom;
        this.followTo = followTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFollowed() {
        return followed;
    }

    public void setFollowed(LocalDateTime followed) {
        this.followed = followed;
    }

    public User getFollowFrom() {
        return followFrom;
    }

    public void setFollowFrom(User followFrom) {
        this.followFrom = followFrom;
    }

    public User getFollowTo() {
        return followTo;
    }

    public void setFollowTo(User followTo) {
        this.followTo = followTo;
    }

    @Override
    public String toString() {
        return "Follow [id=" + id + ", followed=" + followed + ", followFrom=" + followFrom + ", followTo=" + followTo
                + "]";
    }
}