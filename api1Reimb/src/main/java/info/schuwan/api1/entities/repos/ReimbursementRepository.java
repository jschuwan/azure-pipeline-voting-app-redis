package info.schuwan.api1.entities.repos;

import info.schuwan.api1.entities.ReimbStatus;
import info.schuwan.api1.entities.ReimbType;
import info.schuwan.api1.entities.ReimbsUser;
import info.schuwan.api1.entities.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReimbursementRepository extends JpaRepository<Reimbursement, Long> {
    List<Reimbursement> findAllByType(ReimbType type);
    List<Reimbursement> findAllByStatus(ReimbStatus status);
    List<Reimbursement> findAllByManager(ReimbsUser user);
    List<Reimbursement> findAllByEmployee(ReimbsUser user);
    List<Reimbursement> findReimbursementsByAmount(long a);
    List<Reimbursement> findReimbursementsByManagerEmailOrEmployeeEmail(String a, String b);
    List<Reimbursement> findReimbursementsByEmployeeEmailOrderByManagerAsc(String a);
    List<Reimbursement> findReimbursementsByManagerEmailOrderByEmployeeAsc(String a);



}