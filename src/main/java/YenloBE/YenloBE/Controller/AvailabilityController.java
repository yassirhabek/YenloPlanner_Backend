package YenloBE.YenloBE.Controller;

import YenloBE.YenloBE.DTO.AvailabilityDto;
import YenloBE.YenloBE.DTO.OfficeStatusDto;
import YenloBE.YenloBE.DTO.UserDTO;
import YenloBE.YenloBE.Enums.Status;
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

    private AvailabilityService availabilityService;
    private UserService userService;

    @Autowired
    public AvailabilityController(AvailabilityService availabilityService, UserService userService){
        this.availabilityService = availabilityService;
        this.userService = userService;
    }

    // Add Methods
    @PostMapping("/add/day")
    public String addAvailabilityOneDay(@Valid @RequestBody Availability availability) throws ApiRequestException {
        if (userService.findById(availability.getUser().getId()) == null) {
            throw new ApiRequestException("User not found by ID");
        } else {
//            if (userService.findById(availability.getUser().getId()).isSick == true){
//                availability.status = Status.SICK;
//            }
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

    @PostMapping("/leave")
    public String addLeaveBetween(@RequestParam Integer user_id, @RequestParam String start_date, @RequestParam String end_date) throws ApiRequestException, ParseException{
        if (user_id != null && start_date != null && end_date != null) {
            Date startDate = (new SimpleDateFormat("yyyy/MM/dd").parse(start_date));
            Date endDate = (new SimpleDateFormat("yyyy/MM/dd").parse(end_date));

            User user = userService.findById(user_id);
            Optional<List<Availability>> availabilities = availabilityService.getAvailabilityBetween(user_id, startDate, endDate);
            availabilities.get().forEach(item ->
            {
                item.setStatus(Status.LEAVE);
                availabilityService.addAvailabilityOneDay(item);
            });
            return "Changed status to leave";
        } else {
            return "No valid user_id/start_date/end_date";
        }
    }

    // Read Methods
    @GetMapping("/day")
    public UserDTO getAvailabilityOneDay(@RequestParam String date, @RequestParam Integer user_id) throws ApiRequestException, ParseException {
        if (date != null && user_id != null ) {
            Date date1 = (new SimpleDateFormat("yyyy/MM/dd").parse(date));

            User user = userService.findById(user_id);
            UserDTO u = new UserDTO(user, availabilityService.getAvailabilityOneDay(date1, user_id));
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
            UserDTO u = new UserDTO(user, availabilityService.getAvailabilityOneWeek(date1, user_id).orElseThrow());
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

            if (endDate.before(startDate)){
                throw new ApiRequestException("End date is before start date!");
            }

            User user = userService.findById(user_id);
            UserDTO u = new UserDTO(user, availabilityService.getAvailabilityBetween(user_id, startDate, endDate).orElseThrow());
            return u;
        } else {
            return null;
        }
    }

    @GetMapping("/office")
    public List<OfficeStatusDto> getOfficeStatus(@RequestParam String start_date, @RequestParam String end_date) throws ParseException {
        Date startDate = (new SimpleDateFormat("yyyy/MM/dd").parse(start_date));
        Date endDate = (new SimpleDateFormat("yyyy/MM/dd").parse(end_date));
        return availabilityService.getOfficeStatus(startDate, endDate);
    }

    // Delete Methods

    // Update Methods
    @PutMapping("/update/day")
    public String updateAvailabilityDay(@RequestBody Availability availability){
        if (userService.findById(availability.getUser().getId()) == null && availabilityService.findById(availability.getId()) == null){
            return "No availability found for user id: " + availability.getUser().getId() + " and availability id " + availability.getId();
        } else {
            availabilityService.updateAvailabilityDay(availability);
            return "Availability updated";
        }
    }

    @PutMapping("/update/month")
    public String updateAvailabilityMonth(@RequestBody List<Availability> availabilities){
        for (Availability a:availabilities) {
            this.updateAvailabilityDay(a);
        }
        return "Updated Availabilities";
    }
}
