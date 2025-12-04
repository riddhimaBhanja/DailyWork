package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Tutorial;
import com.example.demo.service.TutorialService;

@RestController
@RequestMapping("/api/tutorials")
public class TutorialController {

    private final TutorialService tutorialService;

    public TutorialController(TutorialService tutorialService) {
        this.tutorialService = tutorialService;
    }

    @GetMapping
    public ResponseEntity<List<Tutorial>> getAllTutorials() {
        return ResponseEntity.ok(tutorialService.getAllTutorials());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable Long id) {
        Optional<Tutorial> tutorial = tutorialService.getTutorialById(id);
        return tutorial.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Tutorial>> findByTitle(@RequestParam String title) {
        return ResponseEntity.ok(tutorialService.findByTitle(title));
    }
    @PostMapping
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        return ResponseEntity.ok(tutorialService.createTutorial(tutorial));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        tutorialService.deleteAllTutorials();
        return ResponseEntity.noContent().build();
    }
}
