package YenloBE.YenloBE.DTO;

import YenloBE.YenloBE.Model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserAvatarDTO {
    public int id;
    public String name;
    public String base64;

    public UserAvatarDTO(User user, String base64) {
        this.id = user.getId();
        this.name = user.getName();
        this.base64 = base64;
    }
}
