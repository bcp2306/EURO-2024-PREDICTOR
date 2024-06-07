import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Predictor extends JFrame {
    private JComboBox<TeamItem> team1ComboBox;
    private JComboBox<TeamItem> team2ComboBox;
    private JLabel resultLabel;

    public Predictor() {
        setTitle("Euro 2024 Predictor");
        setSize(400, 300); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // UI
        team1ComboBox = new JComboBox<>(getTeamItems());
        team2ComboBox = new JComboBox<>(getTeamItems());
        team1ComboBox.setSelectedItem(new TeamItem("France", getFlagIcon("France")));
        team2ComboBox.setSelectedItem(new TeamItem("Portugal", getFlagIcon("Portugal")));
        JButton predictButton = new JButton("PREDICT");
        resultLabel = new JLabel("RESULT: ", JLabel.CENTER);

        // Renderer to display flags 
        team1ComboBox.setRenderer(new TeamItemRenderer());
        team2ComboBox.setRenderer(new TeamItemRenderer());

        //Set size
        Dimension comboBoxSize = new Dimension(300, 50); 
        team1ComboBox.setPreferredSize(comboBoxSize);
        team2ComboBox.setPreferredSize(comboBoxSize);

        // Layout
        setLayout(new BorderLayout());

        JPanel comboPanel = new JPanel(new GridLayout(4, 1));
        comboPanel.add(new JLabel("HOME:"));
        comboPanel.add(team1ComboBox);
        comboPanel.add(new JLabel("AWAY:"));
        comboPanel.add(team2ComboBox);

        JPanel predictPanel = new JPanel(new BorderLayout());
        predictPanel.add(predictButton, BorderLayout.CENTER);

        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.add(resultLabel, BorderLayout.CENTER);

        add(comboPanel, BorderLayout.NORTH);
        add(predictPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);

        // Predict button
        predictButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                predictMatch();
            }
        });
    }

    //Get team with flag
    private TeamItem[] getTeamItems() {
        String[] teamNames = getTeamNames();
        TeamItem[] teamItems = new TeamItem[teamNames.length];
        for (int i = 0; i < teamNames.length; i++) {
            teamItems[i] = new TeamItem(teamNames[i], getFlagIcon(teamNames[i]));
        }
        return teamItems;
    }

    //Get flags
    private ImageIcon getFlagIcon(String teamName) {
        String fileName = "/flags/" + teamName + ".png";
        java.net.URL imgURL = getClass().getResource(fileName);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + fileName);
            return new ImageIcon(); 
        }
    }
    
    //Get team names
    private String[] getTeamNames() {
        return new String[]{
                "France", "Portugal", "Belgium", "England",
                "Germany", "Spain", "Italy", "Hungary",
                "Austria", "Netherlands", "Croatia", "Denmark",
                "Turkey", "Ukraine", "Slovakia", "Switzerland",
                "CzechRepublic", "Slovenia", "Romania", "Scotland",
                "Serbia", "Poland", "Albania", "Georgia"
        };
    }

    //Assign team strength using official FIFA ranking (June 2024)
    private Team getTeamByName(String name) {
        switch (name) {
            case "France": return new Team("France", 24);
            case "Portugal": return new Team("Portugal", 21);
            case "Belgium": return new Team("Belgium", 23);
            case "England": return new Team("England", 22);
            case "Germany": return new Team("Germany", 16);
            case "Spain": return new Team("Spain", 19);
            case "Italy": return new Team("Italy", 18);
            case "Hungary": return new Team("Hungary", 11);
            case "Austria": return new Team("Austria", 12);
            case "Netherlands": return new Team("Netherlands", 20);
            case "Croatia": return new Team("Croatia", 17);
            case "Denmark": return new Team("Denmark", 14);
            case "Turkey": return new Team("Turkey", 6);
            case "Ukraine": return new Team("Ukraine", 13);
            case "Slovakia": return new Team("Slovakia", 4);
            case "Switzerland": return new Team("Switzerland", 15);
            case "CzechRepublic": return new Team("CzechRepublic", 8);
            case "Slovenia": return new Team("Slovenia", 3);
            case "Romania": return new Team("Romania", 5);
            case "Scotland": return new Team("Scotland", 7);
            case "Serbia": return new Team("Serbia", 9);
            case "Poland": return new Team("Poland", 10);
            case "Albania": return new Team("Albania", 2);
            case "Georgia": return new Team("Georgia", 1);
            default: return new Team("Unknown", 0);
        }
    }

    //Predict match
    private void predictMatch() {
        TeamItem team1Item = (TeamItem) team1ComboBox.getSelectedItem();
        TeamItem team2Item = (TeamItem) team2ComboBox.getSelectedItem();

        if (team1Item.getName().equals(team2Item.getName())) {
            resultLabel.setText("Result: Select different teams.");
            return;
        }

        Team team1 = getTeamByName(team1Item.getName());
        Team team2 = getTeamByName(team2Item.getName());

        Match match = new Match(team1, team2);
        Team winner = match.predictWinner();

        resultLabel.setText("RESULT: " + winner.getName() + " win");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Predictor().setVisible(true);
            }
        });
    }
}

