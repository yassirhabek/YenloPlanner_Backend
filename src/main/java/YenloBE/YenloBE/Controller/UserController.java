package YenloBE.YenloBE.Controller;

import YenloBE.YenloBE.DTO.UserAvatarDTO;
import YenloBE.YenloBE.Exception.ApiRequestException;
import YenloBE.YenloBE.Model.Photo;
import YenloBE.YenloBE.Model.Role;
import YenloBE.YenloBE.Model.Team;
import YenloBE.YenloBE.Model.User;
import YenloBE.YenloBE.Repo.RoleRepo;
import YenloBE.YenloBE.Service.AvailabilityService;
import YenloBE.YenloBE.Service.PhotoService;
import YenloBE.YenloBE.Service.TeamService;
import YenloBE.YenloBE.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private TeamService teamService;
    private RoleRepo roleRepo;

    @Autowired
    public UserController(UserService userService, PhotoService photoService, AvailabilityService availabilityService, TeamService teamService, RoleRepo roleRepo) {
        this.userService = userService;
        this.photoService = photoService;
        this.availabilityService = availabilityService;
        this.teamService = teamService;
        this.roleRepo = roleRepo;
    }

    @PutMapping("/sick")
    public ResponseEntity<String> callInSick(@RequestParam Boolean isSick, @RequestParam Integer userId) {
        if (userService.findById(userId) == null){
            return new ResponseEntity<>("Invalid userid", HttpStatus.BAD_REQUEST);
        }

        User user = userService.findById(userId).get();

        user.isSick = isSick;
        userService.saveUser(user);
        return new ResponseEntity<>("User is now marked as sick", HttpStatus.OK);
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
    public ResponseEntity<?> getUserAvatar(@RequestParam Integer userId) {
        if (userService.findById(userId) == null){
            return new ResponseEntity<>("Invalid userid", HttpStatus.BAD_REQUEST);
        }

        User user = userService.findById(userId).get();
        Photo photo = photoService.getPhoto(user.photoId);
        byte[] bytes = photo.getData().getData();
        String s = Base64.getEncoder().encodeToString(bytes);
        String string = "data:image/png;base64, " + s;
        UserAvatarDTO userAvatarDTO = new UserAvatarDTO(user, string);

        return new ResponseEntity<>(userAvatarDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) throws ApiRequestException {
        if (userService.findById(id) == null){
            return new ResponseEntity<>("Invalid userid", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<?> findByName(@RequestParam String name) throws ApiRequestException {
        if (userService.findByName(name) == null){
            return new ResponseEntity<>("No user found with that name", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userService.findByName(name), HttpStatus.OK);
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
    public ResponseEntity<String> deleteUser(Integer id) {
        if (userService.findById(id) == null) {
            return new ResponseEntity<>("Invalid usesrid", HttpStatus.BAD_REQUEST);
        }
        User user = userService.findById(id).get();
        deleteUserFromTeams(user);
        deleteUserFromUserRoles(user);
        return new ResponseEntity<>(userService.deleteUser(user), HttpStatus.OK);
    }

    private void deleteUserFromTeams(User user) {
        List<Team> teams = teamService.getUserTeams(user);
        for (Team team:teams) {
            team.user.remove(user);
            teamService.deleteUserFromTeam(team);
        }
    }

    private void deleteUserFromUserRoles(User user) {
        List<Role> roles = roleRepo.findAll();
        for (Role role:roles) {
            role.User.remove(user);
            roleRepo.save(role);
        }
    }

    // Update Methods
    @PutMapping
    public ResponseEntity<String> updateUser(@Valid @RequestBody User user) throws ApiRequestException {
        if (userService.findById(user.getId()) == null){
            return new ResponseEntity<>("Invalid userid", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }
}
