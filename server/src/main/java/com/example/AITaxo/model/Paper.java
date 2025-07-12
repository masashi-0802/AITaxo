// server/src/main/java/com/example/aitaxo/model/Paper.java
package com.example.aitaxo.model;

import javax.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Paper {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String doi;
    private String title;
    private String authors;  // 著者リスト文字列 (簡易実装)

    @ManyToMany(mappedBy = "papers")
    @JsonIgnore  // JSON出力時に論文からモデルリストを無視（循環防止）
    private List<MLModel> models;

}
