package ua.org.hasper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.org.hasper.Entity.MarkStamp;

/**
 * Created by Pavel.Eremenko on 29.09.2016.
 */
public interface MarkStampRepository extends JpaRepository<MarkStamp,Long> {
@Query("select u from MarkStamp u where u.id=:id")
    MarkStamp findById(@Param(value = "id") int id);
}
