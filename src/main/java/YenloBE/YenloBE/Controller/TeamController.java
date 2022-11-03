package YenloBE.YenloBE.Controller;

import YenloBE.YenloBE.Model.Team;
import YenloBE.YenloBE.Model.User;
import YenloBE.YenloBE.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/team") // api path
@CrossOrigin
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/get-team-members")
    public List<User> getTeamMembers(Integer team_id)
    {
        return (List<User>) teamService.getTeamMembers(team_id);
    }

    // Add Methods
    @PostMapping("/add-team")
    public String addTeam(@RequestBody Team team)
    {
        return teamService.addTeam(team);
    }

    // Read Methods

    // Delete Methods

    // Update Methods

}
