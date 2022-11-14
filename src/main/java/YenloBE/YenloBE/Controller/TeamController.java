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

    @GetMapping("{id}")
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

    // Delete Methods

    // Update Methods

}
