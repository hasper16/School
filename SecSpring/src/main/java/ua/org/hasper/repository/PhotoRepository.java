package ua.org.hasper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.org.hasper.Entity.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    @Query("select u from Photo u where u.id=:id")
    Photo findById(@Param("id") long id);
}
