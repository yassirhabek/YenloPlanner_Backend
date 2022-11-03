package YenloBE.YenloBE.Service;

import YenloBE.YenloBE.Model.Availability;
import YenloBE.YenloBE.Repo.AvailabilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvailabilityServiceImpl implements AvailabilityService{

    @Autowired
    private AvailabilityRepo availabilityRepo;
}
