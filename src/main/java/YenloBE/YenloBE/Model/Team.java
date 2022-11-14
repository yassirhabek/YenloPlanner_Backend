package YenloBE.YenloBE.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 255)
    public String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "team_member",
        joinColumns = {
            @JoinColumn(name = "team_id", referencedColumnName = "id")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")
        })
    public List<User> user;

    public Team(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
