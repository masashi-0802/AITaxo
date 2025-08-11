package com.example.aitaxo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "full_name")
    private String fullName;

    // first_thesis_id -> thesis(id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "first_thesis_id",
                foreignKey = @ForeignKey(name = "fk_tag_first_thesis"))
    private Thesis firstThesis;

    // ---- getter / setter ----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public Thesis getFirstThesis() { return firstThesis; }
    public void setFirstThesis(Thesis firstThesis) { this.firstThesis = firstThesis; }
}
