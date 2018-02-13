import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.sqlite.SQLiteException;

/*
Goal: Create a program which parses the file data/playlist.csv and finds the most popular
track on March 20, 2014
 */
public class App{
  public static void main(String args[]){
    try {
      Reader in = new FileReader("data/playlist.csv");
      Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);

      Connection connection = DriverManager.getConnection("jdbc:sqlite::memory:");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);
      statement.executeUpdate("create table playlist (id string, datetime datetime, artist string, title string, year integer, month integer, day integer, day_of_week string, week integer, hour integer);");
      for(CSVRecord record:records){

          String sql = String.format(
              "insert into playlist values('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
              record.get("id"),
              record.get("datetime"),
              record.get("artist").replaceAll("'","\\''"),
              record.get("title").replaceAll("'","\\''"),
              record.get("year"),
              record.get("month"),
              record.get("day"),
              record.get("day_of_week"),
              record.get("week"),
              record.get("hour"));
        try {
          statement.executeUpdate(sql);
        }catch(SQLiteException e1){
          System.out.println(e1.getMessage());
        }
      }

      ResultSet results = statement.executeQuery("SELECT artist, title, COUNT(*) as CT "
                                                    + "FROM playlist "
                                                    + "WHERE year=2014 AND month=3 AND day=20 "
                                                    + "GROUP BY year, month, day, artist, title "
                                                    + "ORDER BY CT desc "
                                                    + "LIMIT 1;");
      while(results.next())
      {
        // read the result set
        System.out.println("name = " + results.getString("artist"));
        System.out.println("title = " + results.getString("title"));
        System.out.println("count = " + results.getInt("CT"));
      }
    }catch(Exception e1){
      System.out.println(e1.getMessage());
    }
  }

}
