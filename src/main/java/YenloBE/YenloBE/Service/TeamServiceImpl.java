package YenloBE.YenloBE.Service;

import YenloBE.YenloBE.Model.Team;
import YenloBE.YenloBE.Repo.TeamRepo;
import YenloBE.YenloBE.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    @Override
    public String deleteUserFromTeam(Integer teamId, Integer userId) {
        if (teamRepo.findById(teamId) != null) {
            teamRepo.deleteTeamByUser(teamId, userId);
            return "Deleted instance.";
        }
        else {
            return "Instance not found.";
        }
    }

    @Override
    public List<Team> getUserTeams(Integer teamId, Integer userId) {
        return teamRepo.findTeamsById(teamId, userId);
    }

    @Override
    public Team updateTeam(Team team, Team teamDetails) {
        team.setName(teamDetails.getName());
        return teamRepo.save(team);
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
