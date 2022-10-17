package YenloBE.YenloBE.Service;

import YenloBE.YenloBE.Exception.ResourceNotFoundException;
import YenloBE.YenloBE.Model.User;
import YenloBE.YenloBE.Repo.UserRepo;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User createUser(User user)
    {
        return userRepo.save(user);
    }

    @Override
    public ResponseEntity<User> findById(Integer id) throws ResourceNotFoundException {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
        return ResponseEntity.ok().body(user);
    }

    @Override
    public User updateUser(User user)
    {
        return userRepo.save(user);
    }

    public String deleteUser(User user)
    {
        userRepo.delete(user);
        return "Deleted user";
    }
}
