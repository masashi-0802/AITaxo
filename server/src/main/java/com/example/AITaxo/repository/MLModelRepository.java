package com.example.aitaxo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.aitaxo.model.MLModel;

@Repository
public interface MLModelRepository extends JpaRepository<MLModel, Long> {
    @EntityGraph(attributePaths = {"tags", "theses", "tags.firstThesis"})
    @Query("select distinct m from MLModel m")
    List<MLModel> findAll();

    @EntityGraph(attributePaths = {"tags", "theses"})
    List<MLModel> findByNameContaining(String name);

    // --- タグ名で検索 ---
    @EntityGraph(attributePaths = {"tags", "theses"})
    @Query("""
        SELECT DISTINCT m
        FROM MLModel m
        JOIN m.tags t
        WHERE t.name LIKE %:tagName%
        """)
    List<MLModel> findByTagName(String tagName);

    // --- 論文タイトルで検索 ---
    @EntityGraph(attributePaths = {"tags", "theses"})
    @Query("""
        SELECT DISTINCT m
        FROM MLModel m
        JOIN m.theses th
        WHERE th.title LIKE %:title%
        """)
    List<MLModel> findByThesisTitle(String title);
}
