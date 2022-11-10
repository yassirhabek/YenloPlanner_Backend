package YenloBE.YenloBE.Controller;

import YenloBE.YenloBE.Exception.ApiRequestException;
import YenloBE.YenloBE.Model.User;
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
    @PostMapping
    public String createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    // Read Methods
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id) throws ApiRequestException {
        if (userService.findById(id) == null) {
            throw new ApiRequestException("User not found by ID " + id);
        }
        else {
            return userService.findById(id);
        }
    }

//    @GetMapping("/{username}")
//    public User findByUsername(@PathVariable String username) throws ApiRequestException {
//        return userService.findByUsername(username)
//                .orElseThrow(()-> new ApiRequestException("No records found."));
//    }

    // Delete Methods
    @DeleteMapping
    public String deleteUser(Integer id) throws ApiRequestException {
        if (userService.findById(id) == null)
        {
            throw new ApiRequestException("User not found by ID " + id);
        }
        else
        {
            return userService.deleteUser(userService.findById(id));
        }
    }

    // Update Methods
    @PutMapping
    public String updateUser(Integer id, @RequestBody User userDetails) throws ApiRequestException {
        if (userService.findById(id) == null) {
            throw new ApiRequestException("User not found by ID " + id);
        }
        else {
            User user = userService.findById(id);
            user.setEmail(userDetails.getEmail());
            user.setIsManager(userDetails.getIsManager());
            user.setName(userDetails.getName());
            user.setPassword(userDetails.getName());
            return userService.updateUser(user, userDetails);
        }
    }
}
