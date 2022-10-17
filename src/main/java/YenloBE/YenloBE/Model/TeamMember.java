package YenloBE.YenloBE.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @ManyToOne
    public User User;
    public Integer UserFK;

    @ManyToOne
    public Team Team;
    public Integer TeamFK;

    public TeamMember()
    {
    }

    public TeamMember(int id, User member, Integer userId, Team team, Integer teamId) {
        Id = id;
        User = member;
        UserFK = userId;
        Team = team;
        TeamFK = teamId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public YenloBE.YenloBE.Model.User getUser() {
        return User;
    }

    public void setUser(YenloBE.YenloBE.Model.User user) {
        User = user;
    }

    public Integer getUserId() {
        return UserFK;
    }

    public void setUserId(Integer userId) {
        UserFK = userId;
    }

    public YenloBE.YenloBE.Model.Team getTeam() {
        return Team;
    }

    public void setTeam(YenloBE.YenloBE.Model.Team team) {
        Team = team;
    }

    public Integer getTeamId() {
        return TeamFK;
    }

    public void setTeamId(Integer teamId) {
        TeamFK = teamId;
    }
}
