package YenloBE.YenloBE.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.util.Date;


@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @OneToOne
    public User User;
    public Integer Status;
    public Date DateTime;
    public Boolean BeforeMidday = false;

    public Availability(int id, User userid, Integer status, Date dateTime, Boolean beforeMidday) {
        Id = id;
        User = userid;
        Status = status;
        DateTime = dateTime;
        BeforeMidday = beforeMidday;
    }
}
