package com.example.be_movieapp.repository;


import com.example.be_movieapp.model.User;
import com.example.be_movieapp.model.WatchedMovie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchedMovieRepository extends JpaRepository<WatchedMovie, Long> {
    List<WatchedMovie> findByUser_Id(Long userId);
}
