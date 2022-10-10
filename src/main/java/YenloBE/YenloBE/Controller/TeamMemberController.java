package YenloBE.YenloBE.Controller;

import YenloBE.YenloBE.Service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "TeamMember") // api path
@CrossOrigin
public class TeamMemberController {
    @Autowired
    TeamMemberService teamMemberService;

    // Add Methods

    // Read Methods

    // Delete Methods

    // Update Methods
}
