import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/*
Goal: Create a program which parses the file data/playlist.csv and finds the most popular
track on March 20, 2014
 */
public class App{

  private static String hash(CSVRecord record){
    return record.get("artist") + "|" + record.get("title");
  }

  private static Iterable<CSVRecord> map(Iterable<CSVRecord> records){
    ArrayList<CSVRecord> results = new ArrayList<>();
    for (CSVRecord record : records) {
      String year = record.get("year");
      String month = record.get("month");
      String day = record.get("day");

      if(year.equals("2014") && month.equals("03") && day.equals("20")){
        results.add(record);
      }
    }

    return results;
  }

  private static HashMap<String, Integer> reduce(Iterable<CSVRecord> records){
    HashMap<String, Integer> results = new HashMap<>();
    for (CSVRecord record : records) {
      String hashKey = hash(record);
      if(results.containsKey(hashKey)){
        Integer counter = results.get(hashKey);
        results.put(hashKey, counter+1);
      }
      else{
        results.put(hashKey, 1);
      }
    }
    return results;
  }

  public static void main(String args[]){
    try {
      Reader in = new FileReader("data/playlist.csv");
      Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
      Iterable<CSVRecord> filteredRecords = map(records);
      Map<String, Integer> counts = reduce(filteredRecords);

      Integer max = 0;
      String artistTrack = null;
      for(String key: counts.keySet()){
        if(counts.get(key) > max){
          max =counts.get(key);
          artistTrack = key;
        }
      }

      System.out.println(artistTrack + " " + max.toString());

    }catch(Exception e1){
      System.out.println(e1.getMessage());
    }
  }

}