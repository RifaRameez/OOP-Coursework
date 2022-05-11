import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Formula1ChampionshipManager implements ChampionshipManager {

    ArrayList<Formula1Driver> formula1DriverArrayList = new ArrayList<>();
    ArrayList<Race> raceArrayList = new ArrayList<>();
    ArrayList<Race> randomRaceArrayList = new ArrayList<>();

    public ArrayList<Race> getRaceArrayList() {
        return raceArrayList;
    }

    public void setRaceArrayList(ArrayList<Race> raceArrayList) {
        this.raceArrayList = raceArrayList;
    }

    public ArrayList<Formula1Driver> getFormula1DriverArrayList() {
        return formula1DriverArrayList;
    }

    public void setFormula1DriverArrayList(ArrayList<Formula1Driver> formula1DriverArrayList) {
        this.formula1DriverArrayList = formula1DriverArrayList;
    }

    //add new driver
    @Override
    public void addDriver(String driverName, String driverLocation, String teamName) {

        //to check if the team name already exists
        int j = 0;
        for (int i = 0; i < formula1DriverArrayList.size(); i++) {
            if (formula1DriverArrayList.get(i).getTeamName().contains(teamName)) {
                j = 1;
            } else if (!(formula1DriverArrayList.get(i).getTeamName().contains(teamName))) {
                j = 0;
            }
        }
        if (j == 0) {
            Formula1Driver formula1Driver = new Formula1Driver(driverName, driverLocation, teamName);
            formula1DriverArrayList.add(formula1Driver);
            System.out.println("Driver added successfully.");
        } else if (j == 1) {
            System.out.println("Above team name already exists. Driver cannot be added.");
        }
    }

    //delete the driver
    @Override
    public void deleteDriver(String driverName, String teamName) {
        for (Formula1Driver formula1Driver : formula1DriverArrayList) {
            if (formula1Driver.getDriverName().equals(driverName) && formula1Driver.getTeamName().equals(teamName)) {
                formula1DriverArrayList.remove(formula1Driver);
                System.out.println("The diver " + driverName + " and the team " + teamName + " were removed successfully!");
                return;
            }
        }
        System.out.println("Cannot delete the driver. Team or driver does not exist.");
    }

    //change the driver
    @Override
    public void changeDriver(String teamName, String driverName) {
        for (Formula1Driver formula1Driver : formula1DriverArrayList) {
            if (teamName.equals(formula1Driver.getTeamName())) {
                formula1Driver.setDriverName(driverName);
                System.out.println("Driver changed");
                return;
            }
        }
        System.out.println("Team does not exist.");
    }

    //display driver statistics
    @Override
    public void displayStatistics(String driverName) {
        for (Formula1Driver formula1Driver : formula1DriverArrayList) {
            if (formula1Driver.getDriverName().equals(driverName)) {
                System.out.println(" Driver Name: " + formula1Driver.getDriverName());
                System.out.println(" Driver Location: " + formula1Driver.getDriverLocation());
                System.out.println(" Team Name: " + formula1Driver.getTeamName());
                System.out.println(" No of First Position : " + formula1Driver.getFirstPosition());
                System.out.println(" No of Second Position: " + formula1Driver.getSecondPosition());
                System.out.println(" No of Third Position: " + formula1Driver.getThirdPosition());
                System.out.println(" Current Points: " + formula1Driver.getCurrentPoints());
                System.out.println(" No of Races: " + formula1Driver.getNoOfRaces());
                return;
            }
        }
        System.out.println("Please Enter A Valid Driver Name");
    }

    //display statistics in table form
    @Override
    public void displayTable() {
        formula1DriverArrayList.sort(new ScoreComparator());
        System.out.println("                                        Formula1 Championship Table");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format("%-19s %-21s %-5s %-11s %-11s %-11s %-21s   %n", "Driver Name    |", "Team Name    |", "First Position    |", "Second Position    |", "Third Position    |", "Current Points    |", "No of Races    |");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
        for (Formula1Driver formula1Driver : formula1DriverArrayList) {
            System.out.format("%-14s | %-15s | %-24s | %-18s | %-17s | %-17s | %-14s | %n", formula1Driver.getDriverName(), formula1Driver.getTeamName(),
                    formula1Driver.getFirstPosition(), formula1Driver.getSecondPosition(), formula1Driver.getThirdPosition(),
                    formula1Driver.getCurrentPoints(), formula1Driver.getNoOfRaces());
        }
    }

    //add new race
    @Override
    public void addRace(LocalDate dates) {
        HashMap<Integer, Formula1Driver> racePosition = new HashMap<>();

        Scanner sc = new Scanner(System.in);
        for (Formula1Driver formula1Driver : formula1DriverArrayList) {

            int position;
            System.out.println("Enter the position of " + formula1Driver + " :");
            while (!sc.hasNextInt()) {
                System.out.println("\nInvalid ... operation number should be integer");
                System.out.print("\nEnter the operation number : ");
                sc.next();
            }
            position = sc.nextInt();
            if (position > formula1DriverArrayList.size()) {
                System.out.print("\nEnter the correct number : ");
                position = sc.nextInt();
            }
            switch (position) {
                case 1:
                    formula1Driver.setPointsPerRace(25);
                    formula1Driver.setFirstPosition(formula1Driver.getFirstPosition() + 1);
                    formula1Driver.setCurrentPoints(formula1Driver.getCurrentPoints() + formula1Driver.getPointsPerRace());
                    formula1Driver.setNoOfRaces(formula1Driver.getNoOfRaces() + 1);
                    setFormula1DriverArrayList(formula1DriverArrayList);
                    break;
                case 2:
                    formula1Driver.setPointsPerRace(18);
                    formula1Driver.setSecondPosition(formula1Driver.getSecondPosition() + 1);
                    formula1Driver.setCurrentPoints(formula1Driver.getCurrentPoints() + formula1Driver.getPointsPerRace());
                    formula1Driver.setNoOfRaces(formula1Driver.getNoOfRaces() + 1);
                    setFormula1DriverArrayList(formula1DriverArrayList);
                    break;
                case 3:
                    formula1Driver.setPointsPerRace(15);
                    formula1Driver.setThirdPosition(formula1Driver.getThirdPosition() + 1);
                    formula1Driver.setCurrentPoints(formula1Driver.getCurrentPoints() + formula1Driver.getPointsPerRace());
                    formula1Driver.setNoOfRaces(formula1Driver.getNoOfRaces() + 1);
                    setFormula1DriverArrayList(formula1DriverArrayList);

                    break;
                case 4:
                    formula1Driver.setPointsPerRace(12);
                    formula1Driver.setCurrentPoints(formula1Driver.getCurrentPoints() + formula1Driver.getPointsPerRace());
                    formula1Driver.setNoOfRaces(formula1Driver.getNoOfRaces() + 1);
                    setFormula1DriverArrayList(formula1DriverArrayList);

                    break;
                case 5:
                    formula1Driver.setPointsPerRace(10);
                    formula1Driver.setCurrentPoints(formula1Driver.getCurrentPoints() + formula1Driver.getPointsPerRace());
                    formula1Driver.setNoOfRaces(formula1Driver.getNoOfRaces() + 1);
                    setFormula1DriverArrayList(formula1DriverArrayList);

                    break;
                case 6:
                    formula1Driver.setPointsPerRace(8);
                    formula1Driver.setCurrentPoints(formula1Driver.getCurrentPoints() + formula1Driver.getPointsPerRace());
                    formula1Driver.setNoOfRaces(formula1Driver.getNoOfRaces() + 1);
                    setFormula1DriverArrayList(formula1DriverArrayList);

                    break;
                case 7:
                    formula1Driver.setPointsPerRace(6);
                    formula1Driver.setCurrentPoints(formula1Driver.getCurrentPoints() + formula1Driver.getPointsPerRace());
                    formula1Driver.setNoOfRaces(formula1Driver.getNoOfRaces() + 1);
                    setFormula1DriverArrayList(formula1DriverArrayList);

                    break;
                case 8:
                    formula1Driver.setPointsPerRace(4);
                    formula1Driver.setCurrentPoints(formula1Driver.getCurrentPoints() + formula1Driver.getPointsPerRace());
                    formula1Driver.setNoOfRaces(formula1Driver.getNoOfRaces() + 1);
                    setFormula1DriverArrayList(formula1DriverArrayList);

                    break;
                case 9:
                    formula1Driver.setPointsPerRace(2);
                    formula1Driver.setCurrentPoints(formula1Driver.getCurrentPoints() + formula1Driver.getPointsPerRace());
                    formula1Driver.setNoOfRaces(formula1Driver.getNoOfRaces() + 1);
                    setFormula1DriverArrayList(formula1DriverArrayList);
                    break;
                case 10:
                    formula1Driver.setPointsPerRace(1);
                    formula1Driver.setCurrentPoints(formula1Driver.getCurrentPoints() + formula1Driver.getPointsPerRace());
                    formula1Driver.setNoOfRaces(formula1Driver.getNoOfRaces() + 1);
                    setFormula1DriverArrayList(formula1DriverArrayList);
                    break;
            }
            racePosition.put(position, formula1Driver);
        }
        Race race = new Race(dates, racePosition);
        raceArrayList.add(race);

    }

    //saving data in file
    @Override
    public void saveFile() throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream("Formula1Driver.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(formula1DriverArrayList);
        objectOutputStream.close();
        fileOutputStream.close();

        FileOutputStream raceOutput = new FileOutputStream("RaceDetails.ser");
        ObjectOutputStream output = new ObjectOutputStream(raceOutput);
        output.writeObject(raceArrayList);
        output.close();
        raceOutput.close();

        FileOutputStream randomRaceOutput = new FileOutputStream("RandomRaceDetails.ser");
        ObjectOutputStream randomOutput = new ObjectOutputStream(randomRaceOutput);
        randomOutput.writeObject(randomRaceArrayList);
        randomOutput.close();
        randomRaceOutput.close();

        System.out.println("Document Saved successfully !");
    }

    //retrieving data from saved file
    public void loadFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("Formula1Driver.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();

            formula1DriverArrayList = (ArrayList<Formula1Driver>) object;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileInputStream = new FileInputStream("RaceDetails.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object obj = objectInputStream.readObject();

            raceArrayList = (ArrayList<Race>) obj;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileInputStream = new FileInputStream("RandomRaceDetails.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object obj = objectInputStream.readObject();

            randomRaceArrayList = (ArrayList<Race>) obj;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    //display GUI
    @Override
    public void displayGUI() {
        JFrame frame = new JFrame("GUI");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 3));
        panel.setVisible(true); //obj.show;
        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(500, 700);

        JLabel l = new JLabel("      Formula1 Championship ");
        //button 1
        JButton button1 = new JButton("Button 1");
        panel.add(l);
        panel.add(button1);
        ActionListener act1 = new ActionListener() {
            public void actionPerformed(ActionEvent e1) {
                JFrame frame = new JFrame("Tableview");
                JPanel panel = new JPanel();
                frame.setContentPane(panel);
                frame.setSize(500, 500);
                frame.setVisible(true);
                Collections.sort(formula1DriverArrayList, new ScoreComparator());
                JLabel l = new JLabel("Formula 1 Championship Drivers & Statistics");
                panel.add(l);
                String[] columnNames = {"Driver Name", "Team Name", "First Position", "Second Position", "Third Position", "Current Points", "No of Races"};
                DefaultTableModel model = new DefaultTableModel(columnNames, 0);

                for (int i = 0; i < formula1DriverArrayList.size(); i++) {
                    String name = formula1DriverArrayList.get(i).getDriverName();
                    String teamName = formula1DriverArrayList.get(i).getTeamName();
                    int first = formula1DriverArrayList.get(i).getFirstPosition();
                    int second = formula1DriverArrayList.get(i).getSecondPosition();
                    int third = formula1DriverArrayList.get(i).getThirdPosition();
                    int current = formula1DriverArrayList.get(i).getCurrentPoints();
                    int race = formula1DriverArrayList.get(i).getNoOfRaces();
                    Object[] row = {name, teamName, first, second, third, current, race};
                    model.addRow(row);
                }
                final JTable teamTable = new JTable(model);
                teamTable.setFillsViewportHeight(true);

                JScrollPane scrollPane = new JScrollPane(teamTable);
                panel.add(scrollPane);

                teamTable.setOpaque(true);
            }
        };

        //button 2
        JButton button2 = new JButton("Button 2");
        panel.add(button2);
        ActionListener act2 = new ActionListener() {
            public void actionPerformed(ActionEvent e2) {
                JFrame frame = new JFrame("Tableview");
                JPanel panel = new JPanel();
                frame.setContentPane(panel);
                frame.setSize(500, 500);
                frame.setVisible(true);
                Collections.sort(formula1DriverArrayList, new AscendingComparator());
                JLabel l = new JLabel("Formula 1 Championship Drivers & Statistics");
                panel.add(l);
                String[] columnNames = {"Driver Name", "Team Name", "First Position", "Second Position", "Third Position", "Current Points", "No of Races"};
                DefaultTableModel model = new DefaultTableModel(columnNames, 0);

                for (int i = 0; i < formula1DriverArrayList.size(); i++) {
                    String name = formula1DriverArrayList.get(i).getDriverName();
                    String teamName = formula1DriverArrayList.get(i).getTeamName();
                    int first = formula1DriverArrayList.get(i).getFirstPosition();
                    int second = formula1DriverArrayList.get(i).getSecondPosition();
                    int third = formula1DriverArrayList.get(i).getThirdPosition();
                    int current = formula1DriverArrayList.get(i).getCurrentPoints();
                    int race = formula1DriverArrayList.get(i).getNoOfRaces();
                    Object[] row = {name, teamName, first, second, third, current, race};
                    model.addRow(row);
                }
                final JTable teamTable = new JTable(model);
                teamTable.setFillsViewportHeight(true);

                JScrollPane scrollPane = new JScrollPane(teamTable);
                panel.add(scrollPane);

                teamTable.setOpaque(true);
            }
        };

        //button 3
        JButton button3 = new JButton("Button 3");
        panel.add(button3);
        ActionListener act3 = new ActionListener() {
            public void actionPerformed(ActionEvent e3) {
                JFrame frame = new JFrame("Tableview");
                JPanel panel = new JPanel();
                frame.setContentPane(panel);
                frame.setSize(500, 500);
                frame.setVisible(true);
                Collections.sort(formula1DriverArrayList, new PointsComparator());
                JLabel l = new JLabel("Formula 1 Championship Drivers & Statistics");
                panel.add(l);
                String[] columnNames = {"Driver Name", "Team Name", "First Position", "Second Position", "Third Position", "Current Points", "No of Races"};
                DefaultTableModel model = new DefaultTableModel(columnNames, 0);

                for (int i = 0; i < formula1DriverArrayList.size(); i++) {
                    String name = formula1DriverArrayList.get(i).getDriverName();
                    String teamName = formula1DriverArrayList.get(i).getTeamName();
                    int first = formula1DriverArrayList.get(i).getFirstPosition();
                    int second = formula1DriverArrayList.get(i).getSecondPosition();
                    int third = formula1DriverArrayList.get(i).getThirdPosition();
                    int current = formula1DriverArrayList.get(i).getCurrentPoints();
                    int race = formula1DriverArrayList.get(i).getNoOfRaces();
                    Object[] row = {name, teamName, first, second, third, current, race};
                    model.addRow(row);
                }
                final JTable teamTable = new JTable(model);
                teamTable.setFillsViewportHeight(true);

                JScrollPane scrollPane = new JScrollPane(teamTable);
                panel.add(scrollPane);

                teamTable.setOpaque(true);
            }

        };

        //button 4
        JButton button4 = new JButton("Button 4");
        panel.add(button4);
        ActionListener act4 = new ActionListener() {
            public void actionPerformed(ActionEvent e4) {
                JFrame frame = new JFrame("Tableview");
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(11,0));
                frame.setContentPane(panel);
                frame.setSize(500, 500);
                frame.setVisible(true);
                HashMap<Integer, Formula1Driver> position = new HashMap<>();

                Random random = new Random();
                int minDay = (int) LocalDate.of(1900, 1, 1).toEpochDay();
                int maxDay = (int) LocalDate.of(2015, 1, 1).toEpochDay();
                long randomDay = minDay + random.nextInt(maxDay - minDay);

                LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
                JLabel label = new JLabel(String.valueOf(randomDate));
                panel.add(label);

                for (Formula1Driver formula1Driver : formula1DriverArrayList) {
                    int racePosition = (int) ((Math.random() * (9)) + 1);
                    switch (racePosition) {
                        case 1:
                            formula1Driver.setPointsPerRace(25);
                            formula1Driver.setFirstPosition(formula1Driver.getFirstPosition() + 1);
                            formula1Driver.setCurrentPoints(formula1Driver.getCurrentPoints() + formula1Driver.getPointsPerRace());
                            formula1Driver.setNoOfRaces(formula1Driver.getNoOfRaces() + 1);
                            setFormula1DriverArrayList(formula1DriverArrayList);
                            JLabel l1 = new JLabel(formula1Driver.getDriverName() + " : Position 1");
                            panel.add(l1);
                            break;
                        case 2:
                            formula1Driver.setPointsPerRace(18);
                            formula1Driver.setSecondPosition(formula1Driver.getSecondPosition() + 1);
                            formula1Driver.setCurrentPoints(formula1Driver.getCurrentPoints() + formula1Driver.getPointsPerRace());
                            formula1Driver.setNoOfRaces(formula1Driver.getNoOfRaces() + 1);
                            setFormula1DriverArrayList(formula1DriverArrayList);
                            JLabel l2 = new JLabel(formula1Driver.getDriverName() + " : Position 2");
                            panel.add(l2);
                            break;
                        case 3:
                            formula1Driver.setPointsPerRace(15);
                            formula1Driver.setThirdPosition(formula1Driver.getThirdPosition() + 1);
                            formula1Driver.setCurrentPoints(formula1Driver.getCurrentPoints() + formula1Driver.getPointsPerRace());
                            formula1Driver.setNoOfRaces(formula1Driver.getNoOfRaces() + 1);
                            setFormula1DriverArrayList(formula1DriverArrayList);
                            JLabel l3 = new JLabel(formula1Driver.getDriverName() + " : Position 3");
                            panel.add(l3);
                            break;
                        case 4:
                            formula1Driver.setPointsPerRace(12);
                            formula1Driver.setCurrentPoints(formula1Driver.getCurrentPoints() + formula1Driver.getPointsPerRace());
                            formula1Driver.setNoOfRaces(formula1Driver.getNoOfRaces() + 1);
                            setFormula1DriverArrayList(formula1DriverArrayList);
                            JLabel l4 = new JLabel(formula1Driver.getDriverName() + " : Position 4");
                            panel.add(l4);
                            break;
                        case 5:
                            formula1Driver.setPointsPerRace(10);
                            formula1Driver.setCurrentPoints(formula1Driver.getCurrentPoints() + formula1Driver.getPointsPerRace());
                            formula1Driver.setNoOfRaces(formula1Driver.getNoOfRaces() + 1);
                            setFormula1DriverArrayList(formula1DriverArrayList);
                            JLabel l5 = new JLabel(formula1Driver.getDriverName() + " : Position 5");
                            panel.add(l5);
                            break;
                        case 6:
                            formula1Driver.setPointsPerRace(8);
                            formula1Driver.setCurrentPoints(formula1Driver.getCurrentPoints() + formula1Driver.getPointsPerRace());
                            formula1Driver.setNoOfRaces(formula1Driver.getNoOfRaces() + 1);
                            setFormula1DriverArrayList(formula1DriverArrayList);
                            JLabel l6 = new JLabel(formula1Driver.getDriverName() + " : Position 6");
                            panel.add(l6);
                            break;
                        case 7:
                            formula1Driver.setPointsPerRace(6);
                            formula1Driver.setCurrentPoints(formula1Driver.getCurrentPoints() + formula1Driver.getPointsPerRace());
                            formula1Driver.setNoOfRaces(formula1Driver.getNoOfRaces() + 1);
                            setFormula1DriverArrayList(formula1DriverArrayList);
                            JLabel l7 = new JLabel(formula1Driver.getDriverName() + " : Position 7");
                            panel.add(l7);
                            break;
                        case 8:
                            formula1Driver.setPointsPerRace(4);
                            formula1Driver.setCurrentPoints(formula1Driver.getCurrentPoints() + formula1Driver.getPointsPerRace());
                            formula1Driver.setNoOfRaces(formula1Driver.getNoOfRaces() + 1);
                            setFormula1DriverArrayList(formula1DriverArrayList);
                            JLabel l8 = new JLabel(formula1Driver.getDriverName() + " : Position 8");
                            panel.add(l8);
                            break;
                        case 9:
                            formula1Driver.setPointsPerRace(2);
                            formula1Driver.setCurrentPoints(formula1Driver.getCurrentPoints() + formula1Driver.getPointsPerRace());
                            formula1Driver.setNoOfRaces(formula1Driver.getNoOfRaces() + 1);
                            setFormula1DriverArrayList(formula1DriverArrayList);
                            JLabel l9 = new JLabel(formula1Driver.getDriverName() + " : Position 9");
                            panel.add(l9);
                            break;
                        case 10:
                            formula1Driver.setPointsPerRace(1);
                            formula1Driver.setCurrentPoints(formula1Driver.getCurrentPoints() + formula1Driver.getPointsPerRace());
                            formula1Driver.setNoOfRaces(formula1Driver.getNoOfRaces() + 1);
                            setFormula1DriverArrayList(formula1DriverArrayList);
                            JLabel l10 = new JLabel(formula1Driver.getDriverName() + " : Position 10");
                            panel.add(l10);
                            break;
                    }
                    position.put(racePosition, formula1Driver);
                }
                Race randomRace = new Race(randomDate, position);
                randomRaceArrayList.add(randomRace);
                try {
                    saveFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        //button 6
        JButton button6 = new JButton("Button 6");
        panel.add(button6);
        ActionListener act6 = new ActionListener() {
            public void actionPerformed(ActionEvent e6) {
                JFrame frame = new JFrame("Tableview");
                JPanel panel = new JPanel();
                frame.add(panel);
                frame.setContentPane(panel);
                frame.setSize(500, 500);
                frame.setVisible(true);

                ArrayList<LocalDate> dateList = new ArrayList<>();

                for (Race race : raceArrayList) {
                    LocalDate date = race.getRaceDate();
                    dateList.add(date);
                }

                for (Race randomRace : randomRaceArrayList) {
                    LocalDate date = randomRace.getRaceDate();
                    dateList.add(date);
                }

                Collections.sort(dateList);

                DefaultTableModel model = new DefaultTableModel(1, 0);
                JTable table = new JTable(model);
                panel.add(table);
                model.addColumn("Dates", new String[]{"Race Dates"});

                for (int i = 0; i < dateList.size(); i++) {
                    LocalDate dt = dateList.get(i);

                    Object[] row = {" " + dt};
                    model.addRow(row);

                }


            }

        };

        //button 7
        JTextField textField = new JTextField(20);
        panel.add(textField);
        JButton button7 = new JButton("SEARCH");
        panel.add(button7);

        ActionListener act7 = new ActionListener() {
            public void actionPerformed(ActionEvent e7) {
                JFrame frame = new JFrame("Tableview");
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(20, 2));
                frame.add(panel);
                frame.setContentPane(panel);
                frame.setSize(500, 500);
                frame.setVisible(true);

                String textFieldValue = textField.getText();
                for (Formula1Driver formula1Driver : formula1DriverArrayList) {
                    if (formula1Driver.getDriverName().equals(textFieldValue)) {
                        JLabel l = new JLabel("Driver Name : " + formula1Driver.getDriverName());
                        panel.add(l);
                    }
                    for (Race race : raceArrayList) {
                        if (formula1Driver.getDriverName().equals(textFieldValue)) {
                            if (formula1Driver.getDriverName().equals(textFieldValue)) {
                                JLabel l1 = new JLabel("Race Date : " + race.getRaceDate());
                                panel.add(l1);
                            }
                            for (Integer position : race.position.keySet()) {
                                if (race.getPosition().get(position).equals(formula1Driver)) {
                                    JLabel l2 = new JLabel("Race Position" + position);
                                    panel.add(l2);
                                }
                            }
                        }
                    }
                }
            }
        };

        button1.addActionListener(act1);
        button2.addActionListener(act2);
        button3.addActionListener(act3);
        button4.addActionListener(act4);
        button6.addActionListener(act6);
        button7.addActionListener(act7);
    }
}


