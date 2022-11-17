package YenloBE.YenloBE.Service;

import YenloBE.YenloBE.Model.Team;
import YenloBE.YenloBE.Repo.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<Team> getAll() {
        return teamRepo.findAll();
    }

    @Override
    public Optional<Team> findById(Integer id) {
        return teamRepo.findById(id);
    }

    // Checks
    public Boolean checkTeamExists(Integer team_id)
    {
        if (teamRepo.findById(team_id).get() == null) {
            return false;
        }
        return true;
    }
}
