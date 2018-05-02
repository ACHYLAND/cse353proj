import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Vector;

public class CSVReader {
    public ArrayList<Vector> read(String filename) {
            BufferedReader br;
            String line;
            final String COMMA_DELIMITER = ",";

            try {
                br = new BufferedReader(new FileReader(filename));
                ArrayList<Vector> vectors = new ArrayList<>();

                while ((line = br.readLine()) != null) {
                    String[] fvs = (line.split(COMMA_DELIMITER));
                    Vector<Integer> featureVector = new Vector<>(fvs.length);

                    for (int i = 0; i < fvs.length; i++) {
                        featureVector.set(i, Integer.parseInt(fvs[i]));
                    }
                    vectors.add(featureVector);

                }

                return vectors;
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            return null;
    }
}
