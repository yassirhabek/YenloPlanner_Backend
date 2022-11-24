package YenloBE.YenloBE.DTO;

import YenloBE.YenloBE.Model.Availability;
import YenloBE.YenloBE.Model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDTO {
    public int id;
    public String name;
    public List<AvailabilityDto> availabilities;

    public UserDTO(User u) {
        this.id = u.getId();
        this.name = u.getName();
        List<AvailabilityDto> aDto = new ArrayList<>();
        for (Availability a:u.getAvailabilities()) {
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

        List<AvailabilityDto> one = new ArrayList<>();
        List<AvailabilityDto> two = new ArrayList<>();

        for (AvailabilityDto b:a) {
            if(b.getBeforeMidday() == true) {
                one.add(b);
            } else {
                two.add(b);
            }
        }

        if (one.size() != two.size()){
            throw new Exception("INNER ERROR - One or more days don't have 2 availabilities");
        }

        List<AvailabilityDto> three = new ArrayList<>();
        int c = one.size();
        for (int i = 0; i < c; i++) {
            three.add(one.get(0));
            one.remove(0);
            three.add(two.get(0));
            two.remove(0);
        }
        return three;
    }
}
