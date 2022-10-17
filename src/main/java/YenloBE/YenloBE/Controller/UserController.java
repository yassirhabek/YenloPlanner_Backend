package YenloBE.YenloBE.Controller;

import YenloBE.YenloBE.Exception.ResourceNotFoundException;
import YenloBE.YenloBE.Model.User;
import YenloBE.YenloBE.Repo.UserRepo;
import YenloBE.YenloBE.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/user") // api path
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    // Add Methods
    @PostMapping("/create")
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    // Read Methods
    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/id")
    public ResponseEntity<User> getUserByID(Integer id) throws ResourceNotFoundException{
        return userService.findById(id);
    }

    // Delete Methods
    @DeleteMapping("/delete")
    public String deleteUser(Integer id) throws ResourceNotFoundException {
        User user = userService.findById(id).getBody();
        userService.deleteUser(user);
        return "Deleted user";
    }


    // Update Methods
    @PutMapping("/update")
    public User updateUser(Integer id, @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userService.findById(id).getBody();
        user.setEmail(userDetails.getEmail());
        user.setManager(userDetails.getManager());
        user.setName(userDetails.getName());
        user.setPassword(userDetails.getName());
        return userService.updateUser(user);
    }
}
