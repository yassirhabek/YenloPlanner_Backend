package YenloBE.YenloBE.Controller;

import YenloBE.YenloBE.DTO.TeamDTO;
import YenloBE.YenloBE.DTO.UserDTO;
import YenloBE.YenloBE.Exception.ApiRequestException;
import YenloBE.YenloBE.Model.Availability;
import YenloBE.YenloBE.Model.Team;
import YenloBE.YenloBE.Model.User;
import YenloBE.YenloBE.Service.TeamService;
import YenloBE.YenloBE.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/team") // api path
@CrossOrigin
public class TeamController {

    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Team getTeam(@PathVariable Integer id) {
        return teamService.findById(id).get();
    }

    @GetMapping
    public List<Team> getTeams()
    {
        return teamService.getAll();
    }

    @GetMapping("/team-names")
    public List<TeamDTO> getTeamNamesAndIds()
    {
        List<Team> teams = teamService.getAll();
        List<TeamDTO> teamDTOs = new ArrayList<>();
        for (int i = 0; i < teams.toArray().length; i++) {
            TeamDTO teamDTO = new TeamDTO((Team) teams.toArray()[i]);
            teamDTOs.add(teamDTO);
        }
        return teamDTOs;
    }

    // Add Methods
    @PostMapping("/add-team")
    public String addTeam(@RequestBody Team team)
    {
        return teamService.addTeam(team);
    }

    // Read Methods

    @GetMapping("/user-teams")
    public List<Team> getUserTeams(@RequestParam Integer userId) {
        User user = userService.findById(userId);
        return teamService.getUserTeams(user);
    }

    // Delete Methods

    @DeleteMapping("/user")
    public Team removeUserFromTeam(@RequestParam Integer teamId, @RequestParam Integer userId) throws ApiRequestException {
        if (teamService.findById(teamId) != null && userService.findById(userId) != null) {
            Optional<Team> team = teamService.findById(teamId);
            User user = userService.findById(userId);
            team.get().user.remove(user);
            return teamService.deleteUserFromTeam(team.get());
        }
        else {
            throw new ApiRequestException("No records found.");
        }
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

    @PostMapping("/user")
    public Team addTeamUser(Integer teamId, Integer userId) throws ApiRequestException {
        if (teamService.findById(teamId) != null && userService.findById(userId) != null) {
            Optional<Team> team = teamService.findById(teamId);
            User user = userService.findById(userId);
            if (checkTeamContainsUser(user, teamId) == false) {
                team.get().user.add(user);
            }
            return teamService.addTeamUser(team.get());
        }
        else {
            throw new ApiRequestException("No records found.");
        }
    }
    
    public Boolean checkTeamContainsUser(User user, Integer teamId) {
        Team team = teamService.findById(teamId).get();
        for (int i = 0; i < team.user.toArray().length; i++) {
            if (team.user.toArray()[i].equals(user))
            {
                return true;
            }
        }
        return false;
    }
}
