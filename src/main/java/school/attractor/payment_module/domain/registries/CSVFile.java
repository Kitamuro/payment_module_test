package school.attractor.payment_module.domain.registries;

import java.io.*;
import java.util.Arrays;


public class CSVFile {

    public void creatCSVFile() throws Exception{

        String csvFile = "example.csv";
        FileWriter writer = new FileWriter(csvFile);

        CSVUtils.writeLine(writer, Arrays.asList("a", "b", "c", "d"));

        //custom separator + quote
        CSVUtils.writeLine(writer, Arrays.asList("aaa", "bb,b", "cc,c"), ',', '"');

        //custom separator + quote
        CSVUtils.writeLine(writer, Arrays.asList("aaa", "bbb", "cc,c"), '|', '\'');

        //double-quotes
        CSVUtils.writeLine(writer, Arrays.asList("aaa", "bbb", "cc\"c"));


        writer.flush();
        writer.close();
    }

}
