import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeoQuiz {

    private static int currentQuestionIndex = 0;
    private static int score = 0;
    private static JFrame window;
    private static JLabel questionLabel;
    private static JRadioButton[] options;
    private static ButtonGroup optionsGroup;
    private static JButton nextButton;

    private static JLabel timerLabel;
    private static Timer timer;
    private static int timeRemaining = 90;

    private static final String[][] questions = {
        {"What is the capital city of Zambia?", "Lusaka", "Livingstone", "Ndola", "Kitwe", "Lusaka"},
        {"Zambia is landlocked. Which of the following countries does NOT border Zambia?", "Tanzania", "Mozambique", "Botswana", "Namibia", "Mozambique"},
        {"What is the largest river in Zambia?", "Kafue River", "Luangwa River", "Zambezi River", "Chambeshi River", "Zambezi River"},
        {"What is the name of the famous waterfall located on the Zambezi River between Zambia and Zimbabwe?", "Niagara Falls", "Angel Falls", "Victoria Falls", "Iguazu Falls", "Victoria Falls"},
        {"What is the highest point in Zambia?", "Mafinga Hills", "Mumpu Hill", "Nyika Plateau", "Mount Mulanje", "Mafinga Hills"},
        {"Which lake forms part of the border between Zambia and the Democratic Republic of the Congo?", "Lake Tanganyika", "Lake Kariba", "Lake Mweru", "Lake Bangweulu", "Lake Mweru"},
        {"In which province is the Kafue National Park located?", "Eastern Province", "Western Province", "Southern Province", "Central Province", "Central Province"},
        {"What type of climate is predominant in Zambia?", "Tropical rainforest", "Mediterranean", "Arid desert", "Tropical savanna", "Tropical savanna"},
        {"Which Zambian city is known as the 'Copperbelt' region?", "Kitwe", "Lusaka", "Livingstone", "Mongu", "Kitwe"},
        {"What is the main agricultural product grown in the Eastern Province of Zambia?", "Coffee", "Maize", "Cotton", "Tea", "Cotton"},
        {"Which Zambian river flows into Lake Kariba?", "Kafue River", "Luangwa River", "Zambezi River", "Chambeshi River", "Zambezi River"},
        {"Which province in Zambia is the largest by area?", "Western Province", "Northern Province", "Southern Province", "Eastern Province", "Western Province"},
        {"What is the main economic activity in Zambia's Copperbelt region?", "Agriculture", "Mining", "Fishing", "Tourism", "Mining"},
        {"Which wildlife reserve in Zambia is famous for its walking safaris?", "Lower Zambezi National Park", "Liuwa Plain National Park", "South Luangwa National Park", "Kafue National Park", "South Luangwa National Park"},
        {"What body of water forms Zambia's southern border with Zimbabwe?", "Lake Tanganyika", "Lake Kariba", "Zambezi River", "Luapula River", "Zambezi River"},
        {"Which Zambian city is a major tourist destination due to its proximity to Victoria Falls?", "Livingstone", "Lusaka", "Ndola", "Kitwe", "Livingstone"},
        {"What plateau covers much of central Zambia?", "Nyika Plateau", "Mafinga Hills", "Central African Plateau", "Katanga Plateau", "Central African Plateau"},
        {"Which Zambian national park is known for its population of white rhinos?", "Kafue National Park", "South Luangwa National Park", "Mosi-oa-Tunya National Park", "North Luangwa National Park", "Mosi-oa-Tunya National Park"},
        {"What is the main water source for the city of Lusaka?", "Zambezi River", "Kafue River", "Luangwa River", "Lake Kariba", "Kafue River"},
        {"Which geographical feature separates Zambia from Malawi to the east?", "Luangwa River", "Muchinga Escarpment", "Zambezi River", "Kafue River", "Muchinga Escarpment"}
    };

    public GeoQuiz() {
        window = new JFrame("Zam Quiz Mania - Geography of Zambia");
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
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.length) {
                    loadQuestion();
                } else {
                    timer.stop();
                    showResults();
                }
            }
        });

        timerLabel = new JLabel("Time: " + timeRemaining + " seconds");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timerLabel.setForeground(Color.RED);
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        window.add(timerLabel, BorderLayout.NORTH);

        questionPanel.add(Box.createVerticalStrut(20));
        questionPanel.add(nextButton);

        window.add(questionPanel, BorderLayout.CENTER);
        loadQuestion();

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                timerLabel.setText("Time: " + timeRemaining + " seconds");

                if (timeRemaining <= 0) {
                    timer.stop();
                    showResults();
                }
            }
        });
        timer.start();

        window.setVisible(true);
    }

    private static void loadQuestion() {
        String[] currentQuestion = questions[currentQuestionIndex];
        questionLabel.setText("Q" + (currentQuestionIndex + 1) + ": " + currentQuestion[0]);
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

        if (selectedOption != null && selectedOption.equals(questions[currentQuestionIndex][5])) {
            score++;
        }
    }

    private static void showResults() {
        JOptionPane.showMessageDialog(window, "Your score: " + score + "/" + questions.length);
        int choice = JOptionPane.showOptionDialog(window, "Would you like to restart or change genre?", "Quiz Completed",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Restart", "Change Genre"}, "Change Genre");

        if (choice == JOptionPane.YES_OPTION) {
            currentQuestionIndex = 0;
            score = 0;
            timeRemaining = 90; // Reset the timer
            loadQuestion();
            timer.start(); // Restart the timer
        } else {
            Genre.main(new String[0]);
            window.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GeoQuiz());
    }
}
