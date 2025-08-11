package com.example.aitaxo.model;

import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ml_model")
public class MLModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "explain", length = 1000)
    private String explain;

    // 多対多: mlmodel_tag (mlmodel_id, tag_id)
    @ManyToMany
    @JoinTable(
        name = "mlmodel_tag",
        joinColumns = @JoinColumn(name = "mlmodel_id",
                                  foreignKey = @ForeignKey(name = "fk_mlmodeltag_mlmodel")),
        inverseJoinColumns = @JoinColumn(name = "tag_id",
                                         foreignKey = @ForeignKey(name = "fk_mlmodeltag_tag"))
    )
    private Set<Tag> tags = new LinkedHashSet<>();

    // 多対多: mlmodel_thesis (mlmodel_id, thesis_id)
    @ManyToMany
    @JoinTable(
        name = "mlmodel_thesis",
        joinColumns = @JoinColumn(name = "mlmodel_id",
                                  foreignKey = @ForeignKey(name = "fk_mlmodelthesis_mlmodel")),
        inverseJoinColumns = @JoinColumn(name = "thesis_id",
                                         foreignKey = @ForeignKey(name = "fk_mlmodelthesis_thesis"))
    )
    private Set<Thesis> theses = new LinkedHashSet<>();

    // ---- getter / setter ----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getExplain() { return explain; }
    public void setExplain(String explain) { this.explain = explain; }

    public Set<Tag> getTags() { return tags; }
    public void setTags(Set<Tag> tags) { this.tags = tags; }

    public Set<Thesis> getTheses() { return theses; }
    public void setTheses(Set<Thesis> theses) { this.theses = theses; }
}
