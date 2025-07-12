// TagRepository.java
public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByNameContaining(String name);
}
