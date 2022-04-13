package info.schuwan.api1.entities.repos;

import info.schuwan.api1.entities.ReimbsUser;
import info.schuwan.api1.entities.ReimbsUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReimbsUserRepository extends JpaRepository<ReimbsUser, Long> {
    Optional<ReimbsUser> findByEmail(String email);
    Optional <ReimbsUser> findReimbsUserById(int id);
//    Optional<ReimbsUser> findReimbsUserFirstNameAndLastName(String first, String last);
    List<ReimbsUser> findReimbsUserByRoleIs(ReimbsUserRole role);
    List<ReimbsUser> findReimbsUserByManager_Email(String email);


}