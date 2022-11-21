package YenloBE.YenloBE.Repo;

import YenloBE.YenloBE.Model.Team;
import YenloBE.YenloBE.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepo extends JpaRepository<Team, Integer> {
        List<Team> findAllByUser(User user);
}
