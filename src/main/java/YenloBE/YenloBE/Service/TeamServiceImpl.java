package YenloBE.YenloBE.Service;

import YenloBE.YenloBE.Model.Team;
import YenloBE.YenloBE.Model.User;
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
        if (teamRepo.findByName(team.getName()) != null) {
            return "Record already exists.";
        } else {
            teamRepo.save(team);
            return "Team created";
        }
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
    public Team deleteUserFromTeam(Team team) {
        return teamRepo.save(team);
    }

    @Override
    public List<Team> getUserTeams(User user) {
        return teamRepo.findAllByUser(user);
    }

    @Override
    public Team updateTeam(Team team, Team teamDetails) {
        team.setName(teamDetails.getName());
        return teamRepo.save(team);
    }

    @Override
    public Team addTeamUser(Team team) {
        return teamRepo.save(team);
    }

    @Override
    public String deleteTeam(Integer teamId) {
        if (findById(teamId) != null) {
            teamRepo.delete(findById(teamId).get());
            return "Deleted team.";
        }
        else {
            return "No records found.";
        }
    }
}
