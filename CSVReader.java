import com.preprocessor.CSVToLibsvmConverter;

import java.io.*;
import java.util.ArrayList;

class CSVReader {

    ArrayList<int[]> read(String filename) {
            BufferedReader br;
            String line;
            final String COMMA_DELIMITER = ",";

            try {
                br = new BufferedReader(new FileReader(filename));
                ArrayList<int[]> vectors = new ArrayList<>();
                ArrayList<String> vectorStrings = new ArrayList<>();
                ArrayList<String> labelStrings = new ArrayList<>();

                int n = 0;
                while ((line = br.readLine()) != null) {
                    String[] fvs = line.split(COMMA_DELIMITER);
                    int[] featureVector = new int[fvs.length + 1];

                    for (int i = 0; i < fvs.length; i++) {
                        featureVector[i] = Integer.parseInt(fvs[i]);
                    }
                    vectors.add(featureVector);
                    vectorStrings.add(line.substring(1));
                    labelStrings.add(line.substring(0, 1));
                    n++;
                }

                File svmFile = new File("svmFile.txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(svmFile, true));

                String featureVectorString;
                String classLabel;
                /*
                for (int i = 0; i < vectorStrings.size(); i++) {
                    featureVectorString = vectorStrings.get(i);
                    classLabel = labelStrings.get(i);
                    String libSVMRecord = CSVToLibsvmConverter.processRecord(featureVectorString, classLabel);
                    bw.write(libSVMRecord, 0, libSVMRecord.length());
                }*/

                return vectors;
            }
            catch (IOException e) {
                System.out.println("Unable to read file or file not found.");
            }
            catch (NumberFormatException e) {
                System.out.println("File not formatted correctly.");
            }

            return null;
    }

    String[] fileToFeatureVector(String filename) {
        try {
            String[] stringArray = new String[9250];
            BufferedReader br = new BufferedReader(new FileReader(filename));
            for (String s : stringArray)
                s = br.readLine();
            return stringArray;
        }
        catch (IOException e) {
            System.out.println("Unable to read file.");
        }
        return null;
    }

    String[] fileToClassLabel(String filename) {
        try {
            String[] stringArray = new String[9250];
            BufferedReader br = new BufferedReader(new FileReader(filename));
            for (String s : stringArray) {
                s = String.valueOf(br.read());
                System.out.println(s);
                br.readLine();
            }
            return stringArray;
        }
        catch (IOException e) {
            System.out.println("Unable to read file.");
        }
        return null;
    }
}
