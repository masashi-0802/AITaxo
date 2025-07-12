// PaperRepository.java
public interface PaperRepository extends JpaRepository<Thesis, Long> {
    List<Thesis> findByTitleContaining(String title);
}
