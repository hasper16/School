package ua.org.hasper.repository;

import org.springframework.data.domain.Pageable;
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
    List<StudentsGroup> findByName (@Param("name") String name);
    @Query("SELECT u FROM StudentsGroup u where u.id = :id")
    StudentsGroup findById (@Param("id") int id);
    @Query("SELECT u FROM StudentsGroup u where u.name <> :name")
    List<StudentsGroup> allGroupsWithout (@Param("name") String name);
    @Query(value = "select g from StudentsGroup g join g.students u join u.jurnals j join j.markStamps m where m.markType<>'VISIT' group by u order by count(case when m.mark='Accept' then 1 end) desc, count(case when m.mark='m1' then 1 end) desc , count(case when m.mark='m2' then 1 end) desc, count(case when m.mark='m3' then 1 end) DESC, count(case when m.mark='m4' then 1 end) desc, count(case when m.mark='m5' then 1 end) desc, count(case when m.mark='m6' then 1 end) desc, count(case when m.mark='m7' then 1 end) desc, count(case when m.mark='m8' then 1 end) desc, count(case when m.mark='m9' then 1 end) desc, count(case when m.mark='m10' then 1 end) desc, count(case when m.mark='m11' then 1 end) desc, count(case when m.mark='m12' then 1 end) desc")
    List<StudentsGroup> top5Groups(Pageable pageable);
}
