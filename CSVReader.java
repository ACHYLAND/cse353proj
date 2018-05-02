import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Vector;

class CSVReader {

    ArrayList<Vector> read(String filename) {
            BufferedReader br;
            String line;
            final String COMMA_DELIMITER = ",";

            try {
                br = new BufferedReader(new FileReader(filename));
                ArrayList<Vector> vectors = new ArrayList<>();

                while ((line = br.readLine()) != null) {
                    String[] fvs = line.split(COMMA_DELIMITER);
                    Vector<Integer> featureVector = new Vector<>(fvs.length);

                    for (String s : fvs) {
                        featureVector.add(Integer.parseInt(s));
                    }
                    vectors.add(featureVector);

                }

                return vectors;
            }
            catch (IOException e) {
                System.out.println("Unable to read file or file not found.");
            }

            return null;
    }
}
