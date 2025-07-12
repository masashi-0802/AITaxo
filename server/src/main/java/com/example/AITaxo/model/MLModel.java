// server/src/main/java/com/example/aitaxo/model/MLModel.java
package com.example.aitaxo.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class MLModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(length = 1000)
    private String description;

    @ManyToMany  // モデル-タグ: 多対多
    @JoinTable(
        name = "model_tag",  // 中間テーブル名
        joinColumns = @JoinColumn(name = "model_id"),   // モデル側FK
        inverseJoinColumns = @JoinColumn(name = "tag_id")  // タグ側FK
    )
    private List<Tag> tags;

    @ManyToMany  // モデル-論文: 多対多
    @JoinTable(
        name = "model_paper",
        joinColumns = @JoinColumn(name = "model_id"),
        inverseJoinColumns = @JoinColumn(name = "paper_id")
    )
    private List<Paper> papers;

    @ElementCollection  // 発表資料のURLリストを埋め込みコレクションで保持
    private List<String> presentationUrls;

}
