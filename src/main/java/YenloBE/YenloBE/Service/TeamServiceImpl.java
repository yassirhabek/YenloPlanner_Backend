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
    public String addTeam(Team team)
    {
        if (checkTeamExists(team.getId()))
        {
            teamRepo.save(team);
            return "Team created.";
        }
        return "Team already exists by that ID!";
    }

    @Override
    public Team getTeamMembers(Integer teamId) {
        return null;
    }

    // Checks
    public Boolean checkTeamExists(Integer team_id)
    {
        if (teamRepo.findById(team_id) == null) {
            return false;
        }
        return true;
    }
}
