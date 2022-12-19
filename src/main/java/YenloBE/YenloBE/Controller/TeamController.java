package YenloBE.YenloBE.Controller;

import YenloBE.YenloBE.DTO.TeamDTO;
import YenloBE.YenloBE.Exception.ApiRequestException;
import YenloBE.YenloBE.Model.Team;
import YenloBE.YenloBE.Model.User;
import YenloBE.YenloBE.Service.TeamService;
import YenloBE.YenloBE.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/team") // api path
@CrossOrigin
public class TeamController {

    private TeamService teamService;
    private UserService userService;

    @Autowired
    public TeamController(TeamService teamService, UserService userService){
        this.teamService = teamService;
        this.userService = userService;
    }

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

    @PostMapping("/user")
    public ResponseEntity<?> addTeamUser(@RequestParam Integer teamId, @RequestParam Integer userId) throws ApiRequestException {
        if (teamService.findById(teamId) == null){
            return new ResponseEntity<>("Invalid teamid", HttpStatus.BAD_REQUEST);
        }

        if (userService.findById(userId) == null){
            return new ResponseEntity<>("Invalid userid", HttpStatus.BAD_REQUEST);
        }

        if (checkTeamContainsUser(userId, teamId) == false){
            Team team = teamService.findById(teamId).get();
            User user = userService.findById(userId).get();
            team.user.add(user);
            teamService.addTeamUser(team);
            return new ResponseEntity<>("User added to team", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User is already in this team", HttpStatus.BAD_REQUEST);
        }
    }
    // Read Methods

    @GetMapping("/user-teams")
    public List<Team> getUserTeams(@RequestParam Integer userId) {
        User user = userService.findById(userId).get();
        return teamService.getUserTeams(user);
    }

    // Delete Methods

    @DeleteMapping
    public String deleteTeam(@RequestParam Integer teamId) {
        return teamService.deleteTeam(teamId);
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> removeUserFromTeam(@RequestParam Integer teamId, @RequestParam Integer userId) {
        if (userService.findById(userId) == null){
            return new ResponseEntity<>("Invalid userid", HttpStatus.BAD_REQUEST);
        }
        if (teamService.findById(teamId) == null){
            return new ResponseEntity<>("Invalid teamid", HttpStatus.BAD_REQUEST);
        }

        Team team = teamService.findById(teamId).get();
        User user = userService.findById(userId).get();

        team.user.remove(user);
        teamService.deleteUserFromTeam(team);

        return new ResponseEntity<>("User removed from team", HttpStatus.OK);
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


    // Checks

    public Boolean checkTeamContainsUser(Integer userId, Integer teamId) {
        User user = userService.findById(userId).get();
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
