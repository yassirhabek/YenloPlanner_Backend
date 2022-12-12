package YenloBE.YenloBE.Service;

import YenloBE.YenloBE.Model.Team;
import YenloBE.YenloBE.Model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<User> getAllUsers();

//    String createUser(User user, Integer adminId);

    Optional<User> findById(Integer id);

    String updateUser(User user);

    String deleteUser(User user);

    User findByName(String name);

    List<User> findAllManagers();

    void saveUser(User user);
}
