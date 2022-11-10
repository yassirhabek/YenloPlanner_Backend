package YenloBE.YenloBE.Service;

import YenloBE.YenloBE.Model.User;
import YenloBE.YenloBE.Repo.UserRepo;
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
    public String createUser(User user)
    {
        if (!checkUserExists(user))
        {
            userRepo.save(user);
            return "User created.";
        }
        else {
            return "User already exists!";
        }
    }

    @Override
    public User findById(Integer id)  {
        return userRepo.findById(id).get();
    }

    @Override
    public String updateUser(User user, User userDetails)
    {
        if (checkUserExists(userDetails) == false) // check if any of the updated details are already taken
        {
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            user.setName(userDetails.getName());
            user.setIsManager(userDetails.getIsManager());
            userRepo.save(user);
            return "User updated.";
        }
        else {
            return "User details taken.";
        }
    }

    public String deleteUser(User user)
    {
        userRepo.delete(user);
        return "Deleted user";
    }

    // Checks
    public Boolean checkUserExists(User newUser)
    {
        return false; // returns false for now, will fix later
    }
}