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
        return "Default Message";
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

    @GetMapping("/month")
    public List<Availability> getAvailabilityOneMonth(@RequestParam Date begin_date, @RequestParam Integer user_id) throws ApiRequestException {
        return availabilityService.getAvailabilityOneMonth(begin_date, user_id)
                .orElseThrow(()-> new ApiRequestException("No records found."));
    }

    // Delete Methods

    // Update Methods
}
