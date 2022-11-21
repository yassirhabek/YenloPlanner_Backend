package YenloBE.YenloBE.Service;

import YenloBE.YenloBE.Model.Team;
import YenloBE.YenloBE.Model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TeamService {
    String addTeam(Team team);
    List<Team> getAll();
    Optional<Team> findById(Integer id);

    String deleteUserFromTeam(Integer teamId, Integer userId);

    List<Team> getUserTeams(Integer teamId, Integer userId);

    Team updateTeam(Team team, Team teamDetails);
}
