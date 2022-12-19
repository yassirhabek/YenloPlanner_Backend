package YenloBE.YenloBE.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 255, unique = true)
    public String email;
    @Column(length = 255)
    public String password;
    @Column(length = 255, unique = true)
    public String name;
    @Column(length = 255)
    public String photoId;
    public Boolean isManager = false;

    public Boolean isSick = false;

    public User(int id, String email, String password, String name, Boolean isManager, String photoid) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.isManager = isManager;
        this.photoId = photoid;
    }
}
