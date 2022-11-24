package YenloBE.YenloBE.Service;

import YenloBE.YenloBE.Exception.ApiRequestException;
import YenloBE.YenloBE.Model.Availability;
import YenloBE.YenloBE.Model.User;
import YenloBE.YenloBE.Repo.AvailabilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
        List<Availability> availabilities = new ArrayList<>();
        Date weekDate = begin_date;
        for (int i = 0; i < 5; i++) {
            for (Availability a:availabilityRepo.findAllByDateTimeAndUserId(weekDate, user_id)) {
                availabilities.add(a);
            };
            Calendar cal = Calendar.getInstance();
            cal.setTime(weekDate);
            cal.add(Calendar.DATE, 1);
            weekDate = cal.getTime();
        }
        return Optional.ofNullable(availabilities);
    }

    @Override
    public Optional<List<Availability>> getAvailabilityBetween(Integer user_id, Date start_date, Date end_date) {
        List<Availability> availabilities = new ArrayList<>();
        for (Availability a:availabilityRepo.findByUserIdAndDateTimeBetween(user_id, start_date, end_date)) {
            availabilities.add(a);
        }
        return Optional.ofNullable(availabilities);
    }

    @Override
    public String updateAvailabilityDay(Availability availability) {

        availabilityRepo.save(availability);
        return "";
    }
}
