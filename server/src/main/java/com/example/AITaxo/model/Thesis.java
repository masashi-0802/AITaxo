package com.example.aitaxo.model;

import jakarta.persistence.*;

@Entity
public class Thesis {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String doi;
    private String title;
    private String authors;

    public Thesis() {

    }

    public Thesis(Long id, String doi, String title, String authors) {
        this.id = id;
        this.doi = doi;
        this.title = title;
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public String getDoi() {
        return doi;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

}
