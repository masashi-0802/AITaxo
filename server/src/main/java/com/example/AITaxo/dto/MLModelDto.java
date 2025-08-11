package com.example.aitaxo.dto;
import java.util.List;
import java.util.Set;

import com.example.aitaxo.model.Thesis;

public record MLModelDto(
    Long id,
    String name,
    String fullName,
    List<String> tags,
    String explain,
    Set<Thesis> theses
) {}