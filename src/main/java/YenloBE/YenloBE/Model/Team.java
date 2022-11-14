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

    @ManyToMany(cascade = CascadeType.ALL)
    public List<User> user;

    public Team(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
