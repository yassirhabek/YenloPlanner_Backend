package YenloBE.YenloBE.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(length = 255)
    public String Email;
    @Column(length = 255)
    public String Password;
    @Column(length = 255)
    public String Name;
    @Column(length = 255)
    public String PhotoId;
    public Boolean IsManager = false;


    public User(int id, String email, String password, String name, Boolean isManager, String photoid) {
        Id = id;
        Email = email;
        Password = password;
        Name = name;
        IsManager = isManager;
        PhotoId = photoid;
    }

    public Boolean getManager() {
        return IsManager;
    }

    public void setManager(Boolean manager) {
        IsManager = manager;
    }
}
