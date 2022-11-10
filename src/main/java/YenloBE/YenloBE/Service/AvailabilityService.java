package YenloBE.YenloBE.Service;

import YenloBE.YenloBE.Model.Availability;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface AvailabilityService {
    Optional<List<Availability>> getAvailabilityOneDay(String date, Integer user_id);

    Optional<List<Availability>> getAvailabilityOneWeek(String begin_date, Integer user_id);

    Optional<List<Availability>> getAvailabilityOneMonth(String begin_date, Integer user_id);
}
