package YenloBE.YenloBE.Service;

import YenloBE.YenloBE.Exception.ResourceNotFoundException;
import YenloBE.YenloBE.Model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();

    User createUser(User user);

    ResponseEntity<User> findById(Integer id) throws ResourceNotFoundException;

    User updateUser(User user);

    String deleteUser(User user);
}
