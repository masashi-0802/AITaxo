// server/src/main/java/com/example/aitaxo/model/Tag.java
package com.example.aitaxo.model;

import javax.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // 初出論文: 多対一（複数タグが1論文を指す可能性あり）
    @ManyToOne
    private Paper firstPaper;

    // モデルとの多対多 (mappedByでTagが非所有者側)
    @ManyToMany(mappedBy = "tags")
    @JsonIgnore  // JSONシリアライズ時にTag側からモデルリストは無視（循環参照防止）
    private List<MLModel> models;

}
