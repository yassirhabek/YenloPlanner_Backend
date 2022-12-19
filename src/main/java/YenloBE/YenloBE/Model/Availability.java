package YenloBE.YenloBE.Model;

import YenloBE.YenloBE.Enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    public User user;
    //public Integer status;
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    public Status status;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    public Date dateTime;
    public Boolean beforeMidday = false;

    public Availability(int id, User userid, Status status, Date dateTime, Boolean _beforeMidday) {
        this.id = id;
        this.user = userid;
        this.status = status;
        this.dateTime = dateTime;
        this.beforeMidday = _beforeMidday;
    }

}