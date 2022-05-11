import java.io.Serializable;
import java.util.Objects;

public class Formula1Driver extends Driver implements Serializable {
    private int firstPosition;
    private int secondPosition;
    private int thirdPosition;
    private int currentPoints;
    private int noOfRaces;
    private int pointsPerRace;

    //constructors
    public Formula1Driver(String driverName, String driverLocation, String teamName) {
        super(driverName, driverLocation, teamName);

    }

    public Formula1Driver() {

    }

    //getters and setters
    public int getFirstPosition() {
        return firstPosition;
    }

    public void setFirstPosition(int firstPosition) {
        this.firstPosition = firstPosition;
    }

    public int getSecondPosition() {
        return secondPosition;
    }

    public void setSecondPosition(int secondPosition) {
        this.secondPosition = secondPosition;
    }

    public int getThirdPosition() {
        return thirdPosition;
    }

    public void setThirdPosition(int thirdPosition) {
        this.thirdPosition = thirdPosition;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public int getNoOfRaces() {
        return noOfRaces;
    }

    public void setNoOfRaces(int noOfRaces) {
        this.noOfRaces = noOfRaces;
    }

    public int getPointsPerRace() {
        return pointsPerRace;
    }

    public void setPointsPerRace(int pointsPerRace) {
        this.pointsPerRace = pointsPerRace;
    }



    @Override
    public String toString() {
        return this.getDriverName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Formula1Driver)) return false;
        if (!super.equals(o)) return false;
        Formula1Driver that = (Formula1Driver) o;
        return firstPosition == that.firstPosition &&
                secondPosition == that.secondPosition &&
                thirdPosition == that.thirdPosition &&
                currentPoints == that.currentPoints &&
                noOfRaces == that.noOfRaces &&
                pointsPerRace == that.pointsPerRace;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstPosition, secondPosition, thirdPosition, currentPoints, noOfRaces, pointsPerRace);
    }

}
