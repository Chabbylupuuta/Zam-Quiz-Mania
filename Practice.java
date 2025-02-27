import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Practice {

    private static JFrame window;
    private static JLabel questionLabel;
    private static JRadioButton[] options;
    private static ButtonGroup optionsGroup;
    private static JButton nextButton;
    private static JButton stopButton;

    private static final String[][] questions = {
        {"What is the capital city of Zambia?", "Lusaka", "Livingstone", "Ndola", "Kitwe", "Lusaka"},
        {"Which famous waterfall is shared between Zambia and Zimbabwe?", "Niagara Falls", "Angel Falls", "Victoria Falls", "Iguazu Falls", "Victoria Falls"},
        {"In which province of Zambia is the South Luangwa National Park located?", "Eastern Province", "Northern Province", "Southern Province", "Central Province", "Eastern Province"},
        {"What is the predominant type of climate in Zambia?", "Tropical rainforest", "Mediterranean", "Arid desert", "Tropical savanna", "Tropical savanna"},
        {"Which lake forms part of the border between Zambia and the Democratic Republic of the Congo?", "Lake Tanganyika", "Lake Kariba", "Lake Mweru", "Lake Bangweulu", "Lake Tanganyika"},
        {"Which Zambian city is known as the 'Copperbelt' region?", "Kitwe", "Lusaka", "Livingstone", "Mongu", "Kitwe"},
        {"What is the main agricultural product grown in the Eastern Province of Zambia?", "Coffee", "Maize", "Cotton", "Tea", "Maize"},
        {"Which Zambian river flows into Lake Kariba?", "Kafue River", "Luangwa River", "Zambezi River", "Chambeshi River", "Zambezi River"},
        {"Which plateau covers much of central Zambia?", "Nyika Plateau", "Mafinga Hills", "Central African Plateau", "Katanga Plateau", "Central African Plateau"},
        {"What is the main water source for the city of Lusaka?", "Zambezi River", "Kafue River", "Luangwa River", "Lake Kariba", "Kafue River"},
        {"Which geographical feature separates Zambia from Malawi to the east?", "Luangwa River", "Muchinga Escarpment", "Zambezi River", "Kafue River", "Muchinga Escarpment"}
    };

    private static Random random = new Random();
    private static int score = 0;

    public Practice() {
        window = new JFrame("Zam Quiz Mania - Practice Mode");
        window.setSize(900, 650);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());

        // Set icon
        ImageIcon imagez = new ImageIcon("Zam Mania Quiz .png");
        window.setIconImage(imagez.getImage());

        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        questionPanel.setBackground(new Color(65, 105, 225));

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        questionLabel.setForeground(Color.WHITE);
        questionPanel.add(questionLabel);

        options = new JRadioButton[4];
        optionsGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setFont(new Font("Arial", Font.PLAIN, 20));
            options[i].setForeground(Color.WHITE);
            options[i].setBackground(new Color(65, 105, 225));
            optionsGroup.add(options[i]);
            questionPanel.add(options[i]);
        }

        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 24));
        nextButton.setBackground(new Color(155, 15, 90));
        nextButton.setForeground(Color.BLACK);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                loadRandomQuestion();
            }
        });

        stopButton = new JButton("Stop Practice");
        stopButton.setFont(new Font("Arial", Font.BOLD, 24));
        stopButton.setBackground(new Color(255, 69, 0));
        stopButton.setForeground(Color.BLACK);
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showResults();
                openMainMenu();
            }
        });

        questionPanel.add(Box.createVerticalStrut(20));
        questionPanel.add(nextButton);
        questionPanel.add(Box.createVerticalStrut(20));
        questionPanel.add(stopButton);

        window.add(questionPanel, BorderLayout.CENTER);
        loadRandomQuestion();

        window.setVisible(true);
    }

    private static void loadRandomQuestion() {
        int randomQuestionIndex = random.nextInt(questions.length);
        String[] currentQuestion = questions[randomQuestionIndex];
        questionLabel.setText("Q: " + currentQuestion[0]);
        for (int i = 0; i < 4; i++) {
            options[i].setText(currentQuestion[i + 1]);
        }
        optionsGroup.clearSelection();
    }

    private static void checkAnswer() {
        String selectedOption = null;
        for (JRadioButton option : options) {
            if (option.isSelected()) {
                selectedOption = option.getText();
                break;
            }
        }

        for (String[] question : questions) {
            if (question[0].equals(questionLabel.getText().substring(3))) {
                if (selectedOption != null && selectedOption.equals(question[5])) {
                    score++;
                }
                break;
            }
        }
    }

    private static void showResults() {
        JOptionPane.showMessageDialog(window, "Your score: " + score);
        int choice = JOptionPane.showOptionDialog(window, "Would you like to restart or change genre?", "Quiz Completed",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Restart", "Back to Main Menu"}, null);

        if (choice == JOptionPane.YES_OPTION) {
            restartQuiz();
        } else {
            openMainMenu();
        }
    }

    private static void restartQuiz() {
        score = 0;
        loadRandomQuestion();
    }

    private static void openMainMenu() {
        // Close current window
        window.dispose();

        // Open the main menu (assuming a Welcome class exists)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Welcome();  // Make sure this class is implemented
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Practice();
            }
        });
    }
}
