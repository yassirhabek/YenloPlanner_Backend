package YenloBE.YenloBE.Controller;

import YenloBE.YenloBE.Exception.ApiRequestException;
import YenloBE.YenloBE.Model.Availability;
import YenloBE.YenloBE.Service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/availability") // api path
@CrossOrigin
public class AvailabilityController {
    @Autowired
    AvailabilityService availabilityService;

    // Add Methods


    // Read Methods
    @GetMapping("/day")
    public List<Availability> getAvailabilityOneDay(@RequestParam String date, @RequestParam Integer user_id) throws ApiRequestException {
        return availabilityService.getAvailabilityOneDay(date, user_id)
                .orElseThrow(()-> new ApiRequestException("No records found."));
    }

    @GetMapping("/week")
    public List<Availability> getAvailabilityOneWeek(@RequestParam String begin_date, @RequestParam Integer user_id) throws ApiRequestException {
        return availabilityService.getAvailabilityOneWeek(begin_date, user_id)
                .orElseThrow(()-> new ApiRequestException("No records found."));
    }

    @GetMapping("/month")
    public List<Availability> getAvailabilityOneMonth(@RequestParam String begin_date, @RequestParam Integer user_id) throws ApiRequestException {
        return availabilityService.getAvailabilityOneMonth(begin_date, user_id)
                .orElseThrow(()-> new ApiRequestException("No records found."));
    }

    // Delete Methods

    // Update Methods
}
