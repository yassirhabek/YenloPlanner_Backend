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
    private int id;
    @OneToOne
    public User user;
    public Integer status;
    @Column(length = 10)
    public String dateTime;
    public Boolean beforeMidday = false;

    public Availability(int id, User userid, Integer status, String _dateTime, Boolean _beforeMidday) {
        this.id = id;
        user = userid;
        this.status = status;
        dateTime = _dateTime;
        beforeMidday = _beforeMidday;
    }
}
