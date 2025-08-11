package com.example.aitaxo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import com.example.aitaxo.dto.TagDto;
import com.example.aitaxo.model.Tag;

import com.example.aitaxo.model.*;
import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByNameContaining(String name);
    @Query("""
           select new com.example.aitaxo.dto.TagDto(
               t.id, t.name, t.fullName,
               ft.id, ft.title
           )
           from Tag t
           left join t.firstThesis ft
           order by t.name asc
           """)
    List<TagDto> findAllAsDto();
}
