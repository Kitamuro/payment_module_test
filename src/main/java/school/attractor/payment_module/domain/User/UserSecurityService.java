package school.attractor.payment_module.domain.User;


import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserSecurityService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    void register(UserRegisterForm userRegisterForm) {
        if( userRepository.existsByEmail ( userRegisterForm.getEmail () )){
            throw new UserAlreadyRegisteredException ();
        }
        var user = User.builder ()
                .email ( userRegisterForm.getEmail () )
                .password ( passwordEncoder.encode ( userRegisterForm.getPassword () ) )
                .build();
        userRepository.save(user);
    }

    public User getUser(String userEmail) {
        return userRepository.findByEmail ( userEmail ).orElseThrow ( ()->new UserNotFoundException ( "User is not found" ) );
    }
}
