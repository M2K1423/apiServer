package com.example.be_movieapp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<WatchedMovie> watchedMovies = new ArrayList<>();

//    public List<WatchedMovie> getWatchedMovies() {
//        return watchedMovies;
//    }
//
//    public void setWatchedMovies(List<WatchedMovie> watchedMovies) {
//        this.watchedMovies = watchedMovies;
//    }

    // ====== GETTERS ======
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // ====== SETTERS ======
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
