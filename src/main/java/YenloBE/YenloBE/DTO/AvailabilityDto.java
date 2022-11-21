package YenloBE.YenloBE.DTO;

import YenloBE.YenloBE.Model.Availability;
import YenloBE.YenloBE.Model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;

@ToString
@Getter
public class AvailabilityDto implements Comparable<AvailabilityDto>{
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    public Date dateTime;
    public Integer status;
    public Boolean beforeMidday;
    public AvailabilityDto(Availability a) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(a.getDateTime());
        cal.add(Calendar.HOUR_OF_DAY, 1);
        this.dateTime = cal.getTime();
        this.status = a.getStatus();
        this.beforeMidday = a.getBeforeMidday();
    }

    @Override
    public int compareTo(AvailabilityDto o) {
        return this.getDateTime().compareTo(o.getDateTime());
    }
}