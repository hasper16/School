package ua.org.hasper.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.org.hasper.Entity.CustomUser;
import ua.org.hasper.Entity.Enums.UserRole;

import java.util.List;

public interface UserRepository extends JpaRepository<CustomUser,Long> {
    @Query("SELECT u FROM CustomUser u where u.login = :login")
    CustomUser findByLogin(@Param("login") String login);

    @Query("SELECT u FROM CustomUser u where u.id = :id")
    CustomUser findById(@Param("id") int id);

    @Query("SELECT u FROM CustomUser u where u.role = :role")
    List<CustomUser> findUsersbyRole(@Param("role") UserRole role);

    @Query("SELECT u FROM CustomUser u where u.role = :role")
    Page<CustomUser> findUsersbyRole(@Param("role") UserRole role,
                                     Pageable pageable);
}
