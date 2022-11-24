package YenloBE.YenloBE.Controller;

import YenloBE.YenloBE.DTO.AvailabilityDto;
import YenloBE.YenloBE.DTO.UserDTO;
import YenloBE.YenloBE.Exception.ApiRequestException;
import YenloBE.YenloBE.Model.Availability;
import YenloBE.YenloBE.Model.User;
import YenloBE.YenloBE.Service.AvailabilityService;
import YenloBE.YenloBE.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(path = "/availability") // api path
@CrossOrigin
public class AvailabilityController {
    @Autowired
    AvailabilityService availabilityService;
    @Autowired
    UserService userService;

    // Add Methods
    @PostMapping("/add/day")
    public String addAvailabilityOneDay(@Valid @RequestBody Availability availability) throws ApiRequestException {
        if (userService.findById(availability.getUser().getId()) == null) {
            throw new ApiRequestException("User not found by ID");
        } else {
            return availabilityService.addAvailabilityOneDay(availability);
        }
    }

    @PostMapping("/add/week")
    public String addAvailabilityOneWeek(@Valid @RequestBody List<Availability> availabilities) {
        for (Availability a:availabilities) {
            this.addAvailabilityOneDay(a);
        }
        return "Availabilities added";
    }

    // Read Methods
    @GetMapping("/day")
    public UserDTO getAvailabilityOneDay(@RequestParam String date, @RequestParam Integer user_id) throws ApiRequestException, ParseException {
        if (date != null && user_id != null ) {
            Date date1 = (new SimpleDateFormat("yyyy/MM/dd").parse(date));

            User user = userService.findById(user_id);
            user.setAvailabilities(availabilityService.getAvailabilityOneDay(date1, user_id).orElseThrow());
            UserDTO u = new UserDTO(user);
            return u;
        } else {
            return null;
        }
    }

    @GetMapping("/week")
    public UserDTO getAvailabilityOneWeek(@RequestParam String date, @RequestParam Integer user_id) throws ApiRequestException, ParseException {
        if (date != null && user_id != null ) {
            Date date1 = (new SimpleDateFormat("yyyy/MM/dd").parse(date));

            User user = userService.findById(user_id);
            user.setAvailabilities(availabilityService.getAvailabilityOneWeek(date1, user_id).orElseThrow());
            UserDTO u = new UserDTO(user);
            return u;
        } else {
            return null;
        }
    }

    @GetMapping("/between")
    public UserDTO getAvailabilityBetween(@RequestParam Integer user_id, @RequestParam String start_date, @RequestParam String end_date) throws ApiRequestException, ParseException {
        if (user_id != null && start_date != null && end_date != null) {
            Date startDate = (new SimpleDateFormat("yyyy/MM/dd").parse(start_date));
            Date endDate = (new SimpleDateFormat("yyyy/MM/dd").parse(end_date));

            User user = userService.findById(user_id);
            user.setAvailabilities(availabilityService.getAvailabilityBetween(user_id, startDate, endDate).orElseThrow());
            UserDTO u = new UserDTO(user);
            return u;
        } else {
            return null;
        }
    }

    // Delete Methods

    // Update Methods
    @PutMapping("/update/day")
    public String updateAvailabilityDay(@RequestBody Availability availability){
        if (userService.findById(availability.getId()) == null){
            return "No availability found for id: " + availability.getId();
        } else {
            availabilityService.updateAvailabilityDay(availability);
            return "Availability updated";
        }
    }

    @PutMapping("/update/month/{id}")
    public String updateAvailabilityMonth(@RequestBody List<Availability> availabilities){
        for (Availability a:availabilities) {
            this.updateAvailabilityDay(a);
        }
        return "Updated Availabilities";
    }
}
