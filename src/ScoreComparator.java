import java.util.Comparator;

public class ScoreComparator implements Comparator<Formula1Driver> {

    //comparator to sort driver's current points in  descending order
    @Override
    public int compare(Formula1Driver f1Driver1, Formula1Driver f1Driver2) {

        if (f1Driver1.getCurrentPoints() > f1Driver2.getCurrentPoints())
            return -1;
        else if (f1Driver1.getCurrentPoints() < f1Driver2.getCurrentPoints())
            return 1;
        else {
            int maxPosition1 = f1Driver1.getFirstPosition();
            int maxPosition2 = f1Driver2.getFirstPosition();
            if (maxPosition1 > maxPosition2)
                return -1;
            else if (maxPosition1 < maxPosition2)
                return 1;
            else return 0;
        }
    }
}

