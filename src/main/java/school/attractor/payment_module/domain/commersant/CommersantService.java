package school.attractor.payment_module.domain.commersant;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommersantService {
    private CommersantRepository commersantRepository;


    public CommersantDTO getCommersant(int id) {
        Commersant commersant = commersantRepository.findById(id).orElseThrow();
        return CommersantDTO.from(commersant);
    }
}
