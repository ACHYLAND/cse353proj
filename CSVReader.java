import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Vector;

class CSVReader {

    ArrayList<double[]> read(String filename) {
            BufferedReader br;
            String line;
            final String COMMA_DELIMITER = ",";

            try {
                br = new BufferedReader(new FileReader(filename));
                ArrayList<double[]> vectors = new ArrayList<>();

                int j = 0;
                while ((line = br.readLine()) != null) {
                    String[] fvs = line.split(COMMA_DELIMITER);
                    double[] featureVector = new double[fvs.length];

                    for (int i = 0; i < fvs.length; i++) {
                        featureVector[i] = Integer.parseInt(fvs[i]);
                    }
                    vectors.add(featureVector);
                    j++;
                }

                return vectors;
            }
            catch (IOException e) {
                System.out.println("Unable to read file or file not found.");
            }

            return null;
    }
}
