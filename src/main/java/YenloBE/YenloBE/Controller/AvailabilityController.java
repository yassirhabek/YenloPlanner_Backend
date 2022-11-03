package YenloBE.YenloBE.Controller;

import YenloBE.YenloBE.Exception.ApiRequestException;
import YenloBE.YenloBE.Model.Availability;
import YenloBE.YenloBE.Service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(path = "/availability") // api path
@CrossOrigin
public class AvailabilityController {
    @Autowired
    AvailabilityService availabilityService;

    // Add Methods


    // Read Methods


    // Delete Methods

    // Update Methods
}
