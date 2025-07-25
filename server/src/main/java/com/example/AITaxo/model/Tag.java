package com.example.aitaxo.model;

import jakarta.persistence.*;

@Entity
public class Tag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String fullName;

    @ManyToOne
    private Thesis firstThesis;

    public Tag() {

    }

    public Tag(Long id, String name, Thesis firstThesis, String fullName) {
        this.id = id;
        this.name = name;
        this.firstThesis = firstThesis;
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Thesis getFirstThesis() {
        return firstThesis;
    }

    public String getFullName() {
        return fullName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstThesis(Thesis firstThesis) {
        this.firstThesis = firstThesis;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
