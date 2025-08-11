package com.example.aitaxo.controller;

import com.example.aitaxo.dto.TagDto;
import com.example.aitaxo.service.TagService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/tags")
public class TagController {

    private final TagService service;

    public TagController(TagService service) {
        this.service = service;
    }

    /** 全タグ（名前昇順）を List で返す */
    @GetMapping
    public List<TagDto> getTags() {
        return service.list();
    }
}
