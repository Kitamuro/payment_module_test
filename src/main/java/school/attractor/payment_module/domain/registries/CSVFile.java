package school.attractor.payment_module.domain.registries;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import school.attractor.payment_module.domain.commersant.Commersant;
import school.attractor.payment_module.domain.commersant.CommersantDTO;
import school.attractor.payment_module.domain.commersant.CommersantRepository;
import school.attractor.payment_module.domain.commersant.CommersantService;

import java.io.FileWriter;
import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
public class CSVFile {

    public void creatCSVFile(CommersantRepository commersantRepository) throws Exception{

       var commersant = commersantRepository.findById(1).get();

        String csvFile = "example.csv";
        FileWriter writer = new FileWriter(csvFile);

        writer.append(commersant.getEmail());


        writer.flush();
        writer.close();
    }

}
