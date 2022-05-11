import java.util.Comparator;

public class PointsComparator implements Comparator<Formula1Driver> {

    //comparator to sort driver's first position in descending order
    @Override
    public int compare(Formula1Driver f1Driver1, Formula1Driver f1Driver2) {

        if (f1Driver1.getFirstPosition() > f1Driver2.getFirstPosition())
            return -1;
        else if (f1Driver1.getFirstPosition() < f1Driver2.getFirstPosition())
            return 1;
        else {
            int currentPoints1 = f1Driver1.getCurrentPoints() - f1Driver2.getCurrentPoints();
            int currentPoints2 = f1Driver2.getCurrentPoints() - f1Driver1.getCurrentPoints();
            if (currentPoints1 > currentPoints2)
                return -1;
            else if (currentPoints1 < currentPoints2)
                return 1;
            else return 0;
        }
    }

}

