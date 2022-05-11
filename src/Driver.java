import java.io.Serializable;
import java.util.Objects;

public class Driver implements Serializable {
    private String driverName;
    private String driverLocation;
    private String teamName;

    //constructors
    public Driver(String driverName, String driverLocation, String teamName) {
        this.driverName = driverName;
        this.driverLocation = driverLocation;
        this.teamName = teamName;
    }

    public Driver() {

    }

    //getters and setters
    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverLocation() {
        return driverLocation;
    }

    public void setDriverLocation(String driverLocation) {
        this.driverLocation = driverLocation;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverName='" + driverName + '\'' +
                ", driverLocation='" + driverLocation + '\'' +
                ", teamName='" + teamName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver)) return false;
        Driver driver = (Driver) o;
        return Objects.equals(driverName, driver.driverName) &&
                Objects.equals(driverLocation, driver.driverLocation) &&
                Objects.equals(teamName, driver.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driverName, driverLocation, teamName);
    }


}
