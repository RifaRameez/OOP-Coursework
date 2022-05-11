import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    private final static Formula1ChampionshipManager f1ChampionshipManager = new Formula1ChampionshipManager();

    public static void main(String[] args) throws IOException {
        f1ChampionshipManager.loadFile();

        Scanner sc = new Scanner(System.in);

        int option;

        do {
            System.out.println("\n-------------------------------");
            System.out.println("\nFormula1 Championship\n");
            System.out.println("-------------------------------\n");
            System.out.println("Enter 1 to add a new driver");
            System.out.println("Enter 2 to change the driver");
            System.out.println("Enter 3 to delete the driver");
            System.out.println("Enter 4 to display statistics of the driver");
            System.out.println("Enter 5 to display the table");
            System.out.println("Enter 6 to add a race");
            System.out.println("Enter 7 to save the information in file");
            System.out.println("Enter 8 to GUI");
            System.out.println("Enter 9 to exit the program");

            System.out.print("Enter the option : ");
            while (!sc.hasNextInt()) {
                System.out.println("\nInvalid value.Option number should be integer");
                System.out.println("Enter the operation number : ");
                sc.next();
            }
            option = sc.nextInt();

            switch (option) {
                case 1:
                    addDriverDetails();
                    break;
                case 2:
                    changeDriver();
                    break;
                case 3:
                    deleteDriver();
                    break;
                case 4:
                    displayClubStats();
                    break;
                case 5:
                    f1ChampionshipManager.displayTable();
                    break;
                case 6:
                    addRaceDetails();
                    break;
                case 7:
                    f1ChampionshipManager.saveFile();
                    break;
                case 8:
                    f1ChampionshipManager.displayGUI();
                    break;
                default:
                    System.out.println("\nEnter the correct option number.\nEntered option number is not defined.");
            }
        } while (option != 9);
    }

    public static void addDriverDetails() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the name of the driver : ");
        String driverName = sc.nextLine();
        String regex = "^[a-zA-Z]+$";
        while (!driverName.matches(regex)) {
            System.out.println("Invalid input. Name should be string");
            System.out.print("\nEnter the name of the driver : ");
            driverName = sc.nextLine();
        }

        System.out.print("Enter the location of the driver : ");
        String location = sc.nextLine();
        while (!location.matches(regex)) {
            System.out.println("Invalid input. Location should be string");
            System.out.print("\nEnter the location of the driver : ");
            location = sc.nextLine();
        }

        System.out.print("Enter the team name : ");
        String teamName = sc.nextLine();
        while (!teamName.matches(regex)) {
            System.out.println("Invalid input. Team name should be string");
            System.out.print("\nEnter the team name : ");
            teamName = sc.nextLine();
        }

        f1ChampionshipManager.addDriver(driverName, location, teamName);
    }

    private static void deleteDriver() {
        System.out.println("Enter the driver name to remove :");
        Scanner sc = new Scanner(System.in);
        String driverName = sc.nextLine();
        String regex = "^[a-zA-Z]+$";
        while (!driverName.matches(regex)) {
            System.out.println("Invalid input. Name should be string");
            System.out.print("\nEnter the name of the driver : ");
            driverName = sc.nextLine();
        }

        System.out.print("Enter the team name : ");
        String teamName = sc.nextLine();
        while (!teamName.matches(regex)) {
            System.out.println("Invalid input. Team name should be string");
            System.out.print("\nEnter the team name of the driver : ");
            teamName = sc.nextLine();
        }
        f1ChampionshipManager.deleteDriver(driverName, teamName);

    }

    private static void changeDriver() {
        System.out.println("Enter the team name to change the driver :");
        Scanner sc = new Scanner(System.in);
        String changeTeam = sc.nextLine();
        String regex = "^[a-zA-Z]+$";
        while (!changeTeam.matches(regex)) {
            System.out.println("Invalid input. Team name should be string");
            System.out.print("\nEnter the team name to change the driver : ");
            changeTeam = sc.nextLine();
        }

        System.out.println("Enter the driver name to change :");
        String driverName = sc.nextLine();
        while (!driverName.matches(regex)) {
            System.out.println("Invalid input. Name should be string");
            System.out.print("\nEnter the driver name to change : ");
            driverName = sc.nextLine();
        }

        f1ChampionshipManager.changeDriver(changeTeam, driverName);
    }

    public static void displayClubStats() {
        System.out.println("Enter the driver name to display stats :");
        Scanner sc = new Scanner(System.in);
        String driverName = sc.nextLine();
        String regex = "^[a-zA-Z]+$";
        while (!driverName.matches(regex)) {
            System.out.println("Invalid input. Name should be string");
            System.out.print("\nEnter the driver name to display stats : ");
            driverName = sc.nextLine();
        }
        f1ChampionshipManager.displayStatistics(driverName);

    }


    public static void addRaceDetails() {
        Scanner sc = new Scanner(System.in);
        LocalDate raceDate = null;

        while (true) {
            try {
                LocalDate start = LocalDate.of(1940, 1, 1);
                LocalDate stop = LocalDate.of(2021, 11, 30);
                System.out.print("\n[*] Enter the match date (dd-MM-yyyy) : ");
                String getDate = sc.next();
                raceDate = LocalDate.parse(getDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                Boolean containsToday = (!raceDate.isBefore(start)) && (raceDate.isBefore(stop));
                if (containsToday) {
                    f1ChampionshipManager.addRace(raceDate);
                    break;
                } else {
                    System.out.println("Date range should be between 01-01-1940 and 30-11-2021");
                }
            } catch (DateTimeParseException e) {
                System.out.println("\nDate should be in dd-mm-yyyy format");
            }
        }
    }
}
