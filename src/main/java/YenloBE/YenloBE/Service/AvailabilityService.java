package YenloBE.YenloBE.Service;

import YenloBE.YenloBE.Model.Availability;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface AvailabilityService {
    String addAvailabilityOneDay(Availability availability);
    Optional<List<Availability>> getAvailabilityOneDay(Date date, Integer user_id);

    Optional<List<Availability>> getAvailabilityOneWeek(Date begin_date, Integer user_id);

    Optional<List<Availability>> getAvailabilityBetween(Integer user_id, Date start_date, Date end_date);
}
