package ua.org.hasper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.org.hasper.Entity.StudentsGroup;

import java.util.List;

/**
 * Created by Pavel.Eremenko on 26.08.2016.
 */
public interface GroupRepository extends JpaRepository<StudentsGroup,Long>{
    @Query("SELECT u FROM StudentsGroup u where u.name = :name")
    StudentsGroup findByName (@Param("name") String name);
    @Query("SELECT u FROM StudentsGroup u where u.id = :id")
    StudentsGroup findById (@Param("id") int id);
    @Query("SELECT u FROM StudentsGroup u where u.name <> :name")
    List<StudentsGroup> allGroupsWithout (@Param("name") String name);
}
