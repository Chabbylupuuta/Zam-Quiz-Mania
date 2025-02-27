import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Options extends JFrame {
    private JComboBox<String> numberOfQuestionsCombo;
    private JComboBox<String> difficultyLevelCombo;
    private JCheckBox soundCheckBox;
    private JButton saveButton;
    private JButton backButton;

    public Options() {
        setTitle("Zam Quiz Mania - Options");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        // Set icon
        ImageIcon imagez = new ImageIcon("Zam Mania Quiz .png");
        setIconImage(imagez.getImage());

        // Number of Questions
        JPanel questionsPanel = new JPanel();
        questionsPanel.add(new JLabel("Number of Questions:"));
        String[] numberOfQuestions = {"10", "15", "20", "25", "30"};
        numberOfQuestionsCombo = new JComboBox<>(numberOfQuestions);
        questionsPanel.add(numberOfQuestionsCombo);
        add(questionsPanel);

        // Difficulty Level
        JPanel difficultyPanel = new JPanel();
        difficultyPanel.add(new JLabel("Difficulty Level:"));
        String[] difficultyLevels = {"Easy", "Medium", "Hard"};
        difficultyLevelCombo = new JComboBox<>(difficultyLevels);
        difficultyPanel.add(difficultyLevelCombo);
        add(difficultyPanel);

        // Sound Settings
        JPanel soundPanel = new JPanel();
        soundCheckBox = new JCheckBox("Sound");
        soundPanel.add(soundCheckBox);
        add(soundPanel);

        // Save Button
        saveButton = new JButton("Save");
        saveButton.setBackground(new Color(155, 15, 90));
        saveButton.setForeground(Color.BLACK);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveOptions();
            }
        });
        add(saveButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBackground(new Color(255, 69, 0));
        backButton.setForeground(Color.BLACK);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backToMainMenu();
            }
        });
        add(backButton);

        setVisible(true);
    }

    private void saveOptions() {
        String numberOfQuestions = (String) numberOfQuestionsCombo.getSelectedItem();
        String difficultyLevel = (String) difficultyLevelCombo.getSelectedItem();
        boolean isSoundEnabled = soundCheckBox.isSelected();

        // Save options to preferences or file (this is just a placeholder for actual implementation)
        System.out.println("Options saved:");
        System.out.println("Number of Questions: " + numberOfQuestions);
        System.out.println("Difficulty Level: " + difficultyLevel);
        System.out.println("Sound: " + (isSoundEnabled ? "Enabled" : "Disabled"));

        JOptionPane.showMessageDialog(this, "Options saved successfully!");
    }

    private void backToMainMenu() {
        dispose();
       
    }

    public static void main(String[] args) {
        new Options();
    }
}
