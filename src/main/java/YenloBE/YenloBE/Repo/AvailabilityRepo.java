package YenloBE.YenloBE.Repo;

import YenloBE.YenloBE.Model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepo extends JpaRepository<Availability, Integer> {
}
