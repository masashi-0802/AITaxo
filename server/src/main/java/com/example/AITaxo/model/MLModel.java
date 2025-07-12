// server/src/main/java/com/example/aitaxo/model/MLModel.java
package com.example.aitaxo.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class MLModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 1000)
    private String explain;

    // 多対多は使えないため、JPA管理外の構造に変更（@ManyToMany削除）
    @Transient
    private Tag[] tags;

    @Transient
    private Thesis[] theses;

    @Transient
    private String[] presentations;

    public MLModel() {
    }

    public MLModel(Long id, String name, String fullName, String explain, Tag[] tags, Thesis[] theses, String[] presentations) {
        this.id = id;
        this.name = name;
        this.explain = explain;
        this.tags = tags;
        this.theses = theses;
        this.presentations = presentations;
    }

    // ====== Getters / Setters ======

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getExplain() {
        return explain;
    }

    public Tag[] getTags() {
        return tags;
    }

    public Thesis[] getTheses() {
        return theses;
    }

    public String[] getPresentations() {
        return presentations;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public void setTags(Tag[] tags) {
        this.tags = tags;
    }

    public void setTheses(Thesis[] theses) {
        this.theses = theses;
    }

    public void setPresentations(String[] presentations) {
        this.presentations = presentations;
    }
}    