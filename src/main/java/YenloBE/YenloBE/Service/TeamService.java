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
    Team deleteUserFromTeam(Team team);
    List<Team> getUserTeams(User user);
    Team updateTeam(Team team, Team teamDetails);
    Team addTeamUser(Team team);
    String deleteTeam(Integer teamId);
}
