package YenloBE.YenloBE.Service;

import YenloBE.YenloBE.Model.Team;
import YenloBE.YenloBE.Model.User;
import YenloBE.YenloBE.Repo.TeamRepo;
import YenloBE.YenloBE.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service

public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TeamRepo teamRepo;

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public String createUser(User user, Integer adminId)
    {
        if (!checkUserExists(user) && findById(adminId).isManager && userRepo.findByEmail(user.email) == null && userRepo.findByName(user.name) == null)
        {
            userRepo.save(user);
            return "User created.";
        }
        else {
            return "Action not permitted.";
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

    @Override
    public User findByName(String username) {
        return userRepo.findByName(username);
    }

    // Checks
    public Boolean checkUserExists(User newUser)
    {
        return false; // returns false for now, will fix later
    }
}