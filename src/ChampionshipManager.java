import java.io.IOException;
import java.time.LocalDate;

public interface ChampionshipManager {
    public void addDriver(String driverName, String driverLocation, String teamName);

    public void deleteDriver(String driverName, String teamName);

    public void changeDriver(String teamName, String driverName);

    public void displayStatistics(String driverName);

    public void displayTable();

    public void displayGUI();

    public void addRace(LocalDate dates);

    public void saveFile() throws IOException;

}
