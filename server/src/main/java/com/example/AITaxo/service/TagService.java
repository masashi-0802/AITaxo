package com.example.aitaxo.service;

import com.example.aitaxo.dto.TagDto;
import com.example.aitaxo.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepo;

    public TagService(TagRepository tagRepo) {
        this.tagRepo = tagRepo;
    }

    @Transactional(readOnly = true)
    public List<TagDto> list() {
        return tagRepo.findAllAsDto(); // ← これでLAZYに触らない
    }
    /** 名前昇順で全タグを返す */
    
    /**public List<TagDto> list() {
        return tagRepo.findAll(Sort.by("name"))
                .stream()
                .map(t -> new TagDto(t.getId(), t.getName(), t.getFullName(), t.getFirstThesis() != null ? t.getFirstThesis().getId() : null,
                t.getFirstThesis() != null ? t.getFirstThesis().getTitle() : null))
                .toList();

    }*/
}
