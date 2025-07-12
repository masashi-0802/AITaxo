<<<<<<< HEAD
package com.example.aitaxo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aitaxo.model.*;
import java.util.List;

@Repository
=======
>>>>>>> 3588bbfd36ca56c959ec35e47a8f7dee37108e88
public interface PaperRepository extends JpaRepository<Thesis, Long> {
    List<Thesis> findByTitleContaining(String title);
}
