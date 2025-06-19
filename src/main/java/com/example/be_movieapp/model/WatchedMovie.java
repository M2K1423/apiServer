package com.example.be_movieapp.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "watched_movies")
public class WatchedMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long watched_movie_id;

    @Column(name = "title")
    private String title;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;
    @Column(name = "slug")
    private String slug;
    @Column(name = "watched_at")
    private LocalDateTime watchedAt;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // ===== GETTERS =====


    public Long getWatched_movie_id() {
        return watched_movie_id;
    }

    public void setWatched_movie_id(Long watched_movie_id) {
        this.watched_movie_id = watched_movie_id;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }





    public LocalDateTime getWatchedAt() {
        return watchedAt;
    }

    public User getUser() {
        return user;
    }

    // ===== SETTERS =====


    public void setTitle(String title) {
        this.title = title;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }





    public void setWatchedAt(LocalDateTime watchedAt) {
        this.watchedAt = watchedAt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public String toString() {
        return "WatchedMovie{" +
                "watched_movie_id=" + watched_movie_id +
                ", title='" + title + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", watchedAt=" + watchedAt +
                ", content='" + content + '\'' +
                ", user=" + user +
                '}';
    }
}


