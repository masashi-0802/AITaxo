package com.example.aitaxo.dto;


public record TagDto(
    Long id,
    String name,
    String fullName,
    Long firstThesisId,
    String firstThesisTitle
) {}