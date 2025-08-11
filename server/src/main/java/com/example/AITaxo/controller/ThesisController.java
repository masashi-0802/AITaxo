package com.example.aitaxo.controller;

import com.example.aitaxo.model.Thesis;
import com.example.aitaxo.repository.ThesisRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theses")
@CrossOrigin(origins = "http://localhost:3000")
public class ThesisController {
    private final ThesisRepository thesisRepo;

    public ThesisController(ThesisRepository thesisRepo) {
        this.thesisRepo = thesisRepo;
    }

    @GetMapping
    public List<Thesis> getAll() {
        return thesisRepo.findAll();
    }
}
