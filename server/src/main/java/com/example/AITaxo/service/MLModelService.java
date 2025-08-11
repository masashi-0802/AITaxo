package com.example.aitaxo.service;

import com.example.aitaxo.dto.MLModelDto;
import com.example.aitaxo.model.Tag;
import com.example.aitaxo.repository.MLModelRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MLModelService {
    private final MLModelRepository repo;

    public MLModelService(MLModelRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public List<MLModelDto> findAll() {
        return repo.findAll().stream()
            .map(m -> new MLModelDto(
                m.getId(),
                m.getName(),
                m.getFullName(),
                m.getTags().stream()
                    .map(Tag::getName)
                    .toList(),
                m.getExplain(),
                m.getTheses()
            ))
            .toList();
    }

    @Transactional(readOnly = true)
    public List<MLModelDto> findByTagName(String tagName) {
        return repo.findByTagName(tagName).stream()
            .map(m -> new MLModelDto(
                m.getId(),
                m.getName(),
                m.getFullName(),
                m.getTags().stream()
                    .map(Tag::getName)
                    .toList(),
                m.getExplain(),
                m.getTheses()
            ))
            .toList();
    }

    @Transactional(readOnly = true)
    public List<MLModelDto> findByName(String name) {
        return repo.findByNameContaining(name).stream()
            .map(m -> new MLModelDto(
                m.getId(),
                m.getName(),
                m.getFullName(),
                m.getTags().stream()
                    .map(Tag::getName)
                    .toList(),
                m.getExplain(),
                m.getTheses()
            ))
            .toList();
    }
}
