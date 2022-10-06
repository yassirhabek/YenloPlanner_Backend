package YenloBE.YenloBE.Service;

import YenloBE.YenloBE.Model.Team;
import org.springframework.stereotype.Service;

@Service
public interface TeamService {
    public Team addTeam(Team team);
}
