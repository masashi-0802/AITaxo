package com.example.aitaxo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aitaxo.model.MLModel;
import java.util.List;

@Repository
public interface MLModelRepository extends JpaRepository<MLModel, Long> {
    // 名前の部分一致検索（例: クエリ文字列でのフィルタ用）
    List<MLModel> findByNameContaining(String name);
    // タグ名での検索（例: 指定タグを含むモデルを取得）
    List<MLModel> findByTags_Name(String tagName);
}
