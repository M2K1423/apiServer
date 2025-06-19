package com.example.be_movieapp.service;

import com.example.be_movieapp.BeMovieAppApplication;
import com.example.be_movieapp.model.User;
import com.example.be_movieapp.model.WatchedMovie;
import com.example.be_movieapp.repository.WatchedMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchedMoveService {
    @Autowired
    private WatchedMovieRepository watchedMovieRepository;

    public List<WatchedMovie> getWatchedMovies(long userId) {
        return watchedMovieRepository.findByUser_Id(userId);
    }
    public WatchedMovie addWatchedMovie(WatchedMovie movie) {
        return watchedMovieRepository.save(movie);
    }
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BeMovieAppApplication.class, args);

        WatchedMoveService watchedMoveService = context.getBean(WatchedMoveService.class);

        System.out.println("hihi"+watchedMoveService.getWatchedMovies(1L));
    }

}
