package YenloBE.YenloBE.Service;

import YenloBE.YenloBE.Model.Team;
import YenloBE.YenloBE.Repo.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    private TeamRepo teamRepo;

    @Override
    public Team addTeam(Team team)
    {
        return teamRepo.save(team);
    }

    @Override
    public Team getTeamMembers(Integer teamId) {
        return null;
    }
}
