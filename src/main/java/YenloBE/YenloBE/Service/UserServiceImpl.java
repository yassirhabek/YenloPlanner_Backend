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
            user.setManager(userDetails.getManager());
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
        // check if the email or username exists within the database
        if (!checkEmailExists(newUser.getEmail()) && !checkUsernameExists(newUser.getName())) {
            return false;
        }
        return true; // return true if no such user details exist
    }

    public Boolean checkEmailExists(String email)
    {
        if (userRepo.findByEmail(email) == null) {
            return false;
        }
        return true;
    }

    public Boolean checkUsernameExists(String username)
    {
        if (userRepo.findByUsername(username) == null) {
            return false;
        }
        return true;
    }
}
