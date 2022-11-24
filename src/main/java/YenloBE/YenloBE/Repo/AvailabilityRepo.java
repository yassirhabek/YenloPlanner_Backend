package YenloBE.YenloBE.Repo;

import YenloBE.YenloBE.Enums.Status;
import YenloBE.YenloBE.Model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AvailabilityRepo extends JpaRepository<Availability, Integer> {
    List<Availability> findAllByDateTimeAndUserId(@Param("dateTime") Date dateTime, @Param("userId") Integer userId);
    List<Availability> findByUserIdAndDateTimeBetween(Integer userId, Date start, Date end);
    List<Availability> findAllByStatus(Status status);
}