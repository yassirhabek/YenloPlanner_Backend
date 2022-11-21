package YenloBE.YenloBE.Controller;

import YenloBE.YenloBE.Exception.ApiRequestException;
import YenloBE.YenloBE.Model.Team;
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
            throw new ApiRequestException("No records found.");
        }
        else {
            return userService.findById(id);
        }
    }

    @GetMapping("/name")
    public User findByName(@RequestParam String name) throws ApiRequestException {
        if (userService.findByName(name) == null) {
            throw new ApiRequestException("No records found.");
        }
        else {
            return userService.findByName(name);
        }
    }

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
            return userService.updateUser(user, userDetails);
        }
    }
}
