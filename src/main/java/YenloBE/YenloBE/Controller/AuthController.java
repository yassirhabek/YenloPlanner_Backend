package YenloBE.YenloBE.Controller;

import YenloBE.YenloBE.DTO.AuthResponseDTO;
import YenloBE.YenloBE.DTO.LoginDTO;
import YenloBE.YenloBE.DTO.RegisterDTO;
import YenloBE.YenloBE.Model.User;
import YenloBE.YenloBE.Repo.UserRepo;
import YenloBE.YenloBE.Security.TokenProvider;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
@CrossOrigin
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    private TokenProvider tokenProvider;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepo userRepo, PasswordEncoder passwordEncoder, TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @GetMapping("/jwt")
    public ResponseEntity<User> jwt(@RequestParam String token) { // get user from jwt

        String name = Jwts.parser()
                .setSigningKey("7158890107710441921695630644172561604793")
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        User user = userRepo.findByName(name);

        // Return user by token
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        if (userRepo.findByName(registerDTO.getUsername()) != null && userRepo.findByEmail(registerDTO.getEmail()) != null) {
            return new ResponseEntity<>("Username/email is already taken.", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setIsManager(false); // new users are not managers by default. An existing manager can manually promote others
        userRepo.save(user);

        return new ResponseEntity<>("User registered success.", HttpStatus.OK);
    }
}
