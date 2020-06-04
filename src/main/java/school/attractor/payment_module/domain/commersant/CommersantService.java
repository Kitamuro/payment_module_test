package school.attractor.payment_module.domain.commersant;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@AllArgsConstructor
public class CommersantService {

    private CommersantRepository commersantRepository;
    private final PasswordEncoder passwordEncoder;

    public Commersant findByPrincipal(Principal principal){
       return commersantRepository.findByEmail ( principal.getName ( ) ).orElseThrow (()->new CommersantNotFoundException ( "" ) );
    }

    public CommersantDTO getCommersant(int id) {
        Commersant commersant = commersantRepository.findById(id).orElseThrow();
        return CommersantDTO.from(commersant);
    }

    public CommersantDTO save(CommersantDTO commersantDTO) {
        Commersant commersant = Commersant.from(commersantDTO);
        Commersant save = commersantRepository.save(commersant);
        return CommersantDTO.from(save);
    }

    public void register(CommersantRegistrationDataDTO dataDTO) {
        if( commersantRepository.existsByEmail ( dataDTO.getEmail () )){
            throw new CommersantAlreadyRegisteredException ();
        }
        var commersant = Commersant.builder ()
                .email ( dataDTO.getEmail () )
                .password ( passwordEncoder.encode ( dataDTO.getPassword () ) )
                .build();
        commersantRepository.save(commersant);
    }

}
