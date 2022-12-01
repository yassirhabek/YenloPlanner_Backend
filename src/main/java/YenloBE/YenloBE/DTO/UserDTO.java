package YenloBE.YenloBE.DTO;

import YenloBE.YenloBE.Model.Availability;
import YenloBE.YenloBE.Model.User;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Setter
public class UserDTO {
    public int id;
    public String name;
    public List<AvailabilityDto> availabilities;

    public UserDTO(User u, List<Availability> availabilities) {
        this.id = u.getId();
        this.name = u.getName();
        List<AvailabilityDto> aDto = new ArrayList<>();
        for (Availability a:availabilities) {
            aDto.add(new AvailabilityDto(a));
        }

        try {
            this.availabilities = this.SortDateAndMidday(aDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<AvailabilityDto> SortDateAndMidday(List<AvailabilityDto> a) throws Exception {
        Collections.sort(a);
        List<AvailabilityDto> result = new ArrayList<>();

        for (int i = 0; i < a.size(); i++) {
            AvailabilityDto cur = a.get(i); // get current in list
            if (i == a.size() - 1) { // stop loop when on last index
                result.add(cur);
                break;
            }

            AvailabilityDto next = a.get(i+1); // get next in list

            if ((cur.getDateTime().compareTo(next.getDateTime()) == 0) && !cur.getBeforeMidday()) {
                result.add(next); // if the current date equals the next and beforeMidday is 0,
                i++;              // add the next item to list and skip next loop
            }

            result.add(cur); // add current
        }
        return result;
    }
}
