package ua.org.hasper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.org.hasper.Entity.Subject;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    @Query("SELECT u FROM Subject u where u.name = :subname")
    List<Subject> findByName(@Param("subname") String subname);

    @Query("SELECT u FROM Subject u where u.id = :id")
    Subject findById(@Param("id") int id);
}
