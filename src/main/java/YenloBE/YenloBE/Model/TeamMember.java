package YenloBE.YenloBE.Model;

import javax.persistence.*;
import java.util.List;

@Embeddable
public class TeamMember {

    @ManyToOne
    public User User;

    @ManyToOne
    public Team Team;

    public TeamMember()
    {
    }

    public TeamMember(YenloBE.YenloBE.Model.User user, YenloBE.YenloBE.Model.Team team) {
        User = user;
        Team = team;
    }


}
