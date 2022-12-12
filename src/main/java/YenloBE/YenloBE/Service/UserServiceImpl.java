package YenloBE.YenloBE.Service;

import YenloBE.YenloBE.Model.User;
import YenloBE.YenloBE.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

//    @Override
//    public String createUser(User user, Integer adminId)
//    {
//        if (!checkUserExists(user) && findById(adminId).isManager && userRepo.findByEmail(user.email) == null && userRepo.findByName(user.name) == null)
//        {
//            userRepo.save(user);
//            return "User created.";
//        }
//        else {
//            return "Action not permitted.";
//        }
//    }

    @Override
    public Optional<User> findById(Integer id)  {
        return Optional.ofNullable(userRepo.findById(id).get());
    }

    @Override
    public String updateUser(User user)
    {
        return userRepo.save(user).toString();
    }

    public String deleteUser(User user)
    {
        userRepo.delete(user);
        return "User deleted";
    }

    @Override
    public User findByName(String username) {
        return userRepo.findByName(username);
    }

    @Override
    public List<User> findAllManagers() {
        return userRepo.findAllByIsManagerTrueAndIsSickFalse();
    }

    @Override
    public void saveUser(User user) {
        userRepo.save(user);
    }
}