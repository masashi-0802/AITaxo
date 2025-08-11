package com.example.aitaxo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "thesis")
public class Thesis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String doi;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String authors;

    // ---- getter / setter ----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDoi() { return doi; }
    public void setDoi(String doi) { this.doi = doi; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthors() { return authors; }
    public void setAuthors(String authors) { this.authors = authors; }
}
