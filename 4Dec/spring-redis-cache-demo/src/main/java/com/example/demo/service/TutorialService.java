package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.example.demo.model.Tutorial;
import com.example.demo.repository.TutorialRepository;

@Service
public class TutorialService {

    private final TutorialRepository repo;

    public TutorialService(TutorialRepository repo) {
        this.repo = repo;
    }

    // Cache all tutorials
    @Cacheable(value = "tutorials")
    public List<Tutorial> getAllTutorials() {
        return repo.findAll();
    }
    public List<Tutorial> findByTitle(String title) {
        return repo.findByTitleContaining(title);
    }


    // Cache by id
    @Cacheable(value = "tutorial", key = "#id")
    public Optional<Tutorial> getTutorialById(Long id) {
        return repo.findById(id);
    }

    // Insert & update cache
    @CachePut(value = "tutorial", key = "#result.id")
    public Tutorial createTutorial(Tutorial tutorial) {
        return repo.save(tutorial);
    }

    // Clear caches after delete
    @CacheEvict(value = { "tutorials", "tutorial" }, allEntries = true)
    public void deleteAllTutorials() {
        repo.deleteAll();
    }
}
