package YenloBE.YenloBE.DTO;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class OfficeStatusDto {
    public int id;
    public int inOffice;
    public Date date;

    public OfficeStatusDto(int id, int inOffice, Date date) {
        this.id = id;
        this.inOffice = inOffice;
        this.date = date;
    }
}
