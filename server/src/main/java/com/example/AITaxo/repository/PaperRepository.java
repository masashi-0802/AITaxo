// PaperRepository.java
public interface PaperRepository extends JpaRepository<Paper, Long> {
    List<Paper> findByTitleContaining(String title);
}
