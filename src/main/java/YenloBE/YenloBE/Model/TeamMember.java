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
    public Integer UserId;

    @ManyToOne
    public Team Team;
    public Integer TeamId;

    public TeamMember()
    {
    }

    public TeamMember(int id, User member, Integer userId, Team team, Integer teamId) {
        Id = id;
        User = member;
        UserId = userId;
        Team = team;
        TeamId = teamId;
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
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public YenloBE.YenloBE.Model.Team getTeam() {
        return Team;
    }

    public void setTeam(YenloBE.YenloBE.Model.Team team) {
        Team = team;
    }

    public Integer getTeamId() {
        return TeamId;
    }

    public void setTeamId(Integer teamId) {
        TeamId = teamId;
    }
}
