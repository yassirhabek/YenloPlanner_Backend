package YenloBE.YenloBE.Service;

import YenloBE.YenloBE.Model.Team;
import org.springframework.stereotype.Service;

@Service
public interface TeamService {
    public String addTeam(Team team);
    public Team getTeamMembers(Integer teamId);
}
