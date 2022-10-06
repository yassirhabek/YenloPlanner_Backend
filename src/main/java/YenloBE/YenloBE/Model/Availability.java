package YenloBE.YenloBE.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @ManyToOne
    public User User;
    public Integer UserId;

    public Integer Status;
    public Date DateTime;
    public Boolean BeforeMidday = false;

    public Availability()
    {
    }

    public Availability(int id, YenloBE.YenloBE.Model.User user, Integer userId, Integer status, Date dateTime, Boolean beforeMidday) {
        Id = id;
        User = user;
        UserId = userId;
        Status = status;
        DateTime = dateTime;
        BeforeMidday = beforeMidday;
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

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date dateTime) {
        DateTime = dateTime;
    }

    public Boolean getBeforeMidday() {
        return BeforeMidday;
    }

    public void setBeforeMidday(Boolean beforeMidday) {
        BeforeMidday = beforeMidday;
    }
}
