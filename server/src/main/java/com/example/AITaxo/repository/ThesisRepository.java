package com.example.aitaxo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aitaxo.model.*;
import java.util.List;

@Repository
public interface ThesisRepository extends JpaRepository<Thesis, Long> {
    List<Thesis> findByTitleContaining(String title);
}
