import java.io.FileReader;
import java.io.Reader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/*
Goal: Create a program which parses the file data/playlist.csv and finds the most popular
track on March 20, 2014
 */
public class App{



  public static void main(String args[]){
    try {
      Reader in = new FileReader("data/playlist.csv");
      Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);

    }catch(Exception e1){
      System.out.println(e1.getMessage());
    }
  }

}