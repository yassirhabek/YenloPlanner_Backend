package YenloBE.YenloBE.Service;

import YenloBE.YenloBE.Model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();

    String createUser(User user);

    User findById(Integer id);

    String updateUser(User user, User userDetails);

    String deleteUser(User user);

    User findByName(String name);
}
