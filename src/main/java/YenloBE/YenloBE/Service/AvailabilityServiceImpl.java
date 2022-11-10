package YenloBE.YenloBE.Service;

import YenloBE.YenloBE.Model.Availability;
import YenloBE.YenloBE.Repo.AvailabilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityServiceImpl implements AvailabilityService{

    @Autowired
    private AvailabilityRepo availabilityRepo;

    @Override
    public Optional<List<Availability>> getAvailabilityOneDay(String date, Integer user_id) {
        return Optional.ofNullable(availabilityRepo.findAllByDateTimeAndUserId(date, user_id));
    }
}
