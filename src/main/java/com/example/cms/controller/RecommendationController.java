package com.example.cms.controller;

import com.example.cms.model.entity.Email;
import com.example.cms.model.entity.Recommendation;
import com.example.cms.model.entity.User;
import com.example.cms.model.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@CrossOrigin
@RestController
public class RecommendationController {
    @Autowired
    private final RecommendationRepository repository;

    public RecommendationController(RecommendationRepository repository) {
        this.repository = repository;
    }

    public Recommendation create(User user, int sleepRecommendation) {
        Random random = new Random();
        // Generate a random long value
        long recommendationId = random.nextLong();
        Recommendation recommendation = new Recommendation(recommendationId, sleepRecommendation, user);
        Recommendation newRecommendation = repository.save(recommendation);
        return recommendation;
    }

    @GetMapping("/recommendation/{username}")
    public List<Recommendation> getRecommendation(@PathVariable("username") String username) {
        //give most recent recommendation
        return repository.getRecentRecommendation(username);
    }



}
