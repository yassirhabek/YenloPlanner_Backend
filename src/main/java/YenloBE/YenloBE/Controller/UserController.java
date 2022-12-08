package YenloBE.YenloBE.Controller;

import YenloBE.YenloBE.DTO.UserAvatarDTO;
import YenloBE.YenloBE.Enums.Status;
import YenloBE.YenloBE.Exception.ApiRequestException;
import YenloBE.YenloBE.Model.Photo;
import YenloBE.YenloBE.Model.Team;
import YenloBE.YenloBE.Model.User;
import YenloBE.YenloBE.Service.AvailabilityService;
import YenloBE.YenloBE.Service.PhotoService;
import YenloBE.YenloBE.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/user") // api path
@CrossOrigin
public class UserController {
    private UserService userService;
    private PhotoService photoService;
    private AvailabilityService availabilityService;

    @Autowired
    public UserController(UserService userService, PhotoService photoService, AvailabilityService availabilityService){
        this.userService = userService;
        this.photoService = photoService;
        this.availabilityService = availabilityService;
    }

    // Add Methods
    @PostMapping
    public String createUser(@Valid @RequestBody User user, @RequestParam Integer adminId) {
        return userService.createUser(user, adminId);
    }

    // Read Methods
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/managers")
    public List<User> getManagers() {
        return userService.findAllManagers();
    }

    @GetMapping("/user-info")
    public UserAvatarDTO getUserAvatar(@RequestParam Integer userId) {
        User user = userService.findById(userId);
        Photo photo = photoService.getPhoto(user.photoId);
        byte[] bytes = photo.getData().getData();
        String s = Base64.getEncoder().encodeToString(bytes);
        String string = "data:image/png;base64, " + s;
        UserAvatarDTO userAvatarDTO = new UserAvatarDTO(user, string);
        return userAvatarDTO;
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

    @GetMapping("/available-managers")
    public List<User> findManagersNotSick(@RequestParam String date) throws ParseException {
        Date _date = (new SimpleDateFormat("yyyy/MM/dd").parse(date));
        List<User> users = new ArrayList<>();
        for (User u:userService.findAllManagers()) {
            if (availabilityService.getOfficeStatus(u.getId(), _date) == true) {
                users.add(u);
            }
        }
        return users;
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
