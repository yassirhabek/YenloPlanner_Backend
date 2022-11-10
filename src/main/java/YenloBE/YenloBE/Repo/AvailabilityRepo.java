package YenloBE.YenloBE.Repo;

import YenloBE.YenloBE.Model.Availability;
import YenloBE.YenloBE.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AvailabilityRepo extends JpaRepository<Availability, Integer> {
//    @Query(value = "SELECT * FROM availability WHERE date_time = :dateTime AND user_id = :userId", nativeQuery = true)
    List<Availability> findAllByDateTimeAndUserId(@Param("dateTime") String dateTime, @Param("userId") Integer userId);
}
