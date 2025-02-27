import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TourQuiz {

    private static int currentQuestionIndex = 0;
    private static int score = 0;
    private static JFrame window;
    private static JLabel questionLabel;
    private static JRadioButton[] options;
    private static ButtonGroup optionsGroup;
    private static JButton nextButton;

    private static JLabel timerLabel;
    private static Timer timer;
    private static int timeRemaining = 90; // 1 minute and 30 seconds

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
        {"Which geographical feature separates Zambia from Malawi to the east?", "Luangwa River", "Muchinga Escarpment", "Zambezi River", "Kafue River", "Muchinga Escarpment"},
        {"What is Zambia's largest export?", "Tobacco", "Copper", "Diamonds", "Gold", "Copper"},
        {"Which Zambian city is located on the border with Tanzania?", "Livingstone", "Lusaka", "Kasama", "Nakonde", "Nakonde"},
        {"What is the national bird of Zambia?", "Fish Eagle", "Peacock", "Kingfisher", "Parrot", "Fish Eagle"},
        {"Which Zambian festival is celebrated by the Lozi people?", "Nc'wala", "Kulamba", "Kuomboka", "Chibwela Kumushi", "Kuomboka"},
        {"Which Zambian national park is famous for its walking safaris?", "Kafue National Park", "South Luangwa National Park", "Lower Zambezi National Park", "Liuwa Plain National Park", "South Luangwa National Park"},
        {"What is the currency of Zambia?", "Kwacha", "Rand", "Shilling", "Pound", "Kwacha"},
        {"Which Zambian city is known for its annual agricultural show?", "Kitwe", "Choma", "Lusaka", "Livingstone", "Lusaka"},
        {"What is the name of Zambia's national football team?", "The Copper Bullets", "The Elephants", "The Black Stars", "The Eagles", "The Copper Bullets"},
        {"Which Zambian river is a tributary of the Zambezi?", "Luangwa River", "Kafue River", "Chambeshi River", "All of the above", "All of the above"}
    };

    public TourQuiz() {
        window = new JFrame("Zam Quiz Mania - Tour Zambia");
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
            // Open Genre.java
            openGenreSelector();
            window.dispose();
        }
    }
    private static void restartQuiz() {
        currentQuestionIndex = 0;
        score = 0;
        timeRemaining = 90; // reset to 1 minute and 30 seconds
        loadQuestion();
        timer.start();
    }
 private static void openGenreSelector() {
        // Open Genre.java class or any genre selection logic here
        Genre.main(new String[0]); // Assuming Genre.java is your genre selection class
    }
    public static void main(String[] args) {
        new TourQuiz();
    }
}
