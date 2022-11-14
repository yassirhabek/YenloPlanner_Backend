package YenloBE.YenloBE.Service;

import YenloBE.YenloBE.Exception.ApiRequestException;
import YenloBE.YenloBE.Model.Availability;
import YenloBE.YenloBE.Model.User;
import YenloBE.YenloBE.Repo.AvailabilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityServiceImpl implements AvailabilityService{

    @Autowired
    private AvailabilityRepo availabilityRepo;

    @Override
    public String addAvailabilityOneDay(Availability availability)
    {
        availabilityRepo.save(availability);
        return "availability created.";
    }

    @Override
    public Optional<List<Availability>> getAvailabilityOneDay(Date date, Integer user_id) {
        return Optional.ofNullable(availabilityRepo.findAllByDateTimeAndUserId(date, user_id));
    }

    @Override
    public Optional<List<Availability>> getAvailabilityOneWeek(Date begin_date, Integer user_id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Availability>> getAvailabilityOneMonth(Date begin_date, Integer user_id) {
        return Optional.empty();
    }
}
