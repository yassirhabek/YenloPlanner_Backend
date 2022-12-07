package YenloBE.YenloBE.Security;

import YenloBE.YenloBE.Model.User;
import YenloBE.YenloBE.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepo userRepo;

    @Autowired
    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByName(username);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), mapIsManagerToAuthority(user.isManager));
        }
        else {
            throw new UsernameNotFoundException("Username not found.");
        }
    }

    private Collection<GrantedAuthority> mapIsManagerToAuthority(Boolean isManager) {
        if (isManager) {
            return Collections.singleton(new SimpleGrantedAuthority("admin"));
        } else {
            return Collections.singleton(new SimpleGrantedAuthority("user"));
        }
    }
}
