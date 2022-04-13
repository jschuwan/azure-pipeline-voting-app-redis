package info.schuwan.api1.services;

import info.schuwan.api1.entities.ReimbsUser;
import info.schuwan.api1.entities.ReimbsUserRole;
import info.schuwan.api1.entities.repos.ReimbsUserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@NoArgsConstructor
@Service
public class  ReimbsUserService {
    // get user by email
    // get user by id

    private ReimbsUserRepository userRepository;

    @Autowired
    public ReimbsUserService(ReimbsUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<ReimbsUser> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<ReimbsUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<ReimbsUser> getUsersByRole(ReimbsUserRole role){
        return userRepository.findReimbsUserByRoleIs(role);
    }

    public List<ReimbsUser> getUsersByManEmail(String email){
        return userRepository.findReimbsUserByManager_Email(email);
    }
}
