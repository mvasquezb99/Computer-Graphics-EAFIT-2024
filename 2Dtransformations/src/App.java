import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws IOException {

        // Open the file
        FileReader fileReader = new FileReader("C:\\Users\\bojan\\OneDrive\\Documents\\SistemasEAFIT\\quintoSemestre\\computacionGrafica\\challenges\\2Dtransformations\\src\\house.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        // Read the lines of the file
        List<String> lines = new ArrayList<>();
        String line = bufferedReader.readLine();
        while (line != null) {
            lines.add(line);
            line = bufferedReader.readLine();
        }

        // Close the file
        bufferedReader.close();

        // Convert each line into an array of two numbers
        Double[][] numbers = new Double[lines.size()][lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(" ");
            if(parts.length >= 2){
                Double[] x = new Double[]{Double.parseDouble(parts[0]), Double.parseDouble(parts[1])};
                numbers[i] = x;
            }else{
                Double[] x = new Double[]{Double.parseDouble(parts[0])};
                numbers[i] = x;
            }
        }

        Canvas c1 = new Canvas(500,500);
        Panel p1 = new Panel(numbers);
        c1.add(p1);
        c1.setVisible(true);
        c1.setLocationRelativeTo(null);
        c1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c1.setTitle("Figura");
        p1.makeApexes();

        //! ----------------------------------------------------


    }
}
