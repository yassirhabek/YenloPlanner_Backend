package YenloBE.YenloBE.Controller;

import YenloBE.YenloBE.Exception.ApiRequestException;
import YenloBE.YenloBE.Model.Team;
import YenloBE.YenloBE.Model.User;
import YenloBE.YenloBE.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/team") // api path
@CrossOrigin
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/{id}")
    public Team getTeam(@PathVariable Integer id) {
        return teamService.findById(id).get();
    }

    @GetMapping
    public List<Team> getTeams()
    {
        return teamService.getAll();
    }

    // Add Methods
    @PostMapping("/add-team")
    public String addTeam(@RequestBody Team team)
    {
        return teamService.addTeam(team);
    }

    // Read Methods

    @GetMapping("/user-teams")
    public List<Team> getUserTeams(@RequestParam Integer teamId, @RequestParam Integer userId) {
        return teamService.getUserTeams(teamId, userId);
    }

    // Delete Methods

    @DeleteMapping("/team/user")
    public String removeUserFromTeam(@RequestParam Integer teamId, @RequestParam Integer userId) throws ApiRequestException {
        return teamService.deleteUserFromTeam(teamId, userId);
    }

    // Update Methods
    @PutMapping
    public Team updateTeam(Integer id, @RequestBody Team teamDetails) throws ApiRequestException {
        if (teamService.findById(id) != null) {
            Optional<Team> team = teamService.findById(id);
            return teamService.updateTeam(team.get(), teamDetails);
        }
        else {
            throw new ApiRequestException("No records found to update.");
        }
    }
}
