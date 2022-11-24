package YenloBE.YenloBE.DTO;

import YenloBE.YenloBE.Model.Team;

public class TeamDTO {
    public int id;
    public String name;

    public TeamDTO(Team team) {
        this.id = team.getId();
        this.name = team.getName();
    }
}
