package YenloBE.YenloBE.Controller;

import YenloBE.YenloBE.DTO.OfficeStatusDto;
import YenloBE.YenloBE.DTO.UserDTO;
import YenloBE.YenloBE.Enums.Status;
import YenloBE.YenloBE.Exception.ApiRequestException;
import YenloBE.YenloBE.Model.Availability;
import YenloBE.YenloBE.Model.User;
import YenloBE.YenloBE.Service.AvailabilityService;
import YenloBE.YenloBE.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> addAvailabilityOneDay(@Valid @RequestBody Availability availability) {
        if (userService.findById(availability.getUser().getId()) == null){
            return new ResponseEntity<>("Invalid userid", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(availabilityService.addAvailabilityOneDay(availability), HttpStatus.OK);
    }

    @PostMapping("/add/week")
    public String addAvailabilityOneWeek(@Valid @RequestBody List<Availability> availabilities) {
        for (Availability a:availabilities) {
            this.addAvailabilityOneDay(a);
        }
        return "Availabilities added";
    }

    @PostMapping("/leave")
    public ResponseEntity<String> addLeaveBetween(@RequestParam Integer user_id, @RequestParam String start_date, @RequestParam String end_date) throws ApiRequestException, ParseException{
        if (userService.findById(user_id) == null){
            return new ResponseEntity<>("Invalid userid", HttpStatus.BAD_REQUEST);
        }

        if (start_date != null && end_date != null){
            return new ResponseEntity<>("Invalid dates", HttpStatus.BAD_REQUEST);
        }

        Date startDate = (new SimpleDateFormat("yyyy/MM/dd").parse(start_date));
        Date endDate = (new SimpleDateFormat("yyyy/MM/dd").parse(end_date));

        Optional<List<Availability>> availabilities = availabilityService.getAvailabilityBetween(user_id, startDate, endDate);
        availabilities.get().forEach(item ->
        {
            item.setStatus(Status.LEAVE);
            availabilityService.addAvailabilityOneDay(item);
        });

        return new ResponseEntity<>("Changed status to leave", HttpStatus.OK);
    }

    // Read Methods
    @GetMapping("/day")
    public ResponseEntity<?> getAvailabilityOneDay(@RequestParam String date, @RequestParam Integer user_id) throws ParseException {
        if (userService.findById(user_id) == null){
            return new ResponseEntity<>("Invalid userid", HttpStatus.BAD_REQUEST);
        }

        Date date1 = (new SimpleDateFormat("yyyy/MM/dd").parse(date));

        User user = userService.findById(user_id).get();
        UserDTO userDTO = new UserDTO(user, availabilityService.getAvailabilityOneDay(date1, user_id));
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/week")
    public ResponseEntity<?> getAvailabilityOneWeek(@RequestParam String date, @RequestParam Integer user_id) throws ApiRequestException, ParseException {
        if (userService.findById(user_id) == null){
            return new ResponseEntity<>("Invalid userid", HttpStatus.BAD_REQUEST);
        }

        Date date1 = (new SimpleDateFormat("yyyy/MM/dd").parse(date));

        User user = userService.findById(user_id).get();
        UserDTO userDTO = new UserDTO(user, availabilityService.getAvailabilityOneWeek(date1, user_id).orElseThrow());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/between")
    public ResponseEntity<?> getAvailabilityBetween(@RequestParam Integer user_id, @RequestParam String start_date, @RequestParam String end_date) throws ApiRequestException, ParseException {
        if (userService.findById(user_id) == null){
            return new ResponseEntity<>("Invalid userid", HttpStatus.BAD_REQUEST);
        }

        Date startDate = (new SimpleDateFormat("yyyy/MM/dd").parse(start_date));
        Date endDate = (new SimpleDateFormat("yyyy/MM/dd").parse(end_date));

        if (endDate.before(startDate)){
            return new ResponseEntity<>("End date: " + end_date + "is before start date: " + start_date, HttpStatus.BAD_REQUEST);
        }

        User user = userService.findById(user_id).get();
        UserDTO userDTO = new UserDTO(user, availabilityService.getAvailabilityBetween(user_id, startDate, endDate).orElseThrow());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
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
    public ResponseEntity<String> updateAvailabilityDay(@RequestBody Availability availability){
        if (userService.findById(availability.getUser().getId()) == null){
            return new ResponseEntity<>("Invalid userid", HttpStatus.BAD_REQUEST);
        }

        if (availabilityService.findById(availability.getId()) == null){
            return new ResponseEntity<>("Invalid availability id", HttpStatus.BAD_REQUEST);
        }

        availabilityService.updateAvailabilityDay(availability);
        return new ResponseEntity<>("Availability updated", HttpStatus.OK);
    }

    @PutMapping("/update/month")
    public String updateAvailabilityMonth(@RequestBody List<Availability> availabilities){
        for (Availability a:availabilities) {
            this.updateAvailabilityDay(a);
        }
        return "Updated Availabilities";
    }
}
