import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;

public class Race implements Serializable {
    private LocalDate raceDate;
    HashMap<Integer, Formula1Driver> position;

    //constructors
    public Race(LocalDate raceDate, HashMap<Integer, Formula1Driver> position) {
        this.raceDate = raceDate;
        this.position = position;
    }

    public Race() {

    }

    //getters and setters
    public LocalDate getRaceDate() {
        return raceDate;
    }

    public void setRaceDate(LocalDate raceDate) {
        this.raceDate = raceDate;
    }

    public HashMap<Integer, Formula1Driver> getPosition() {
        return position;
    }

    public void setPosition(HashMap<Integer, Formula1Driver> position) {
        this.position = position;
    }


    @Override
    public String toString() {
        return "Race{" +
                "raceDate=" + raceDate +
                ", position=" + position +
                '}';
    }
}
