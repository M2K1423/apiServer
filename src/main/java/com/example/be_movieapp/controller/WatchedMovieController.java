package com.example.be_movieapp.controller;

import com.example.be_movieapp.model.WatchedMovie;
import com.example.be_movieapp.service.WatchedMoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watch-history")
public class WatchedMovieController {

    @Autowired
    private WatchedMoveService watchedService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<WatchedMovie>> getWatchedByUser(@PathVariable Long userId) {
        List<WatchedMovie> list = watchedService.getWatchedMovies(userId);
        if (list == null) {
            return ResponseEntity.notFound().build();
        }
        System.out.println("User " + userId + " có " + list.size() + " phim đã xem.");

        return ResponseEntity.ok(list);
    }
    @PostMapping("/add")
    public ResponseEntity<WatchedMovie> addWatchedMovie(@RequestBody WatchedMovie movie) {
        WatchedMovie saved = watchedService.addWatchedMovie(movie);
        return ResponseEntity.ok(saved);
    }
}

