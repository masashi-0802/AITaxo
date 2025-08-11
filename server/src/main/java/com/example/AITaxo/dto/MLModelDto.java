package com.example.aitaxo.dto;

public record MLModelDto(
    Long id,
    String name,
    String fullName,
    java.util.List<String> tags
) {}