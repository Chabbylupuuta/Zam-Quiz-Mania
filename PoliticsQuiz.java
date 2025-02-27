import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PoliticsQuiz {

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
        {"Who is the current President of Zambia?", "Hakainde Hichilema", "Edgar Lungu", "Levy Mwanawasa", "Kenneth Kaunda", "Hakainde Hichilema"},
        {"What is the name of Zambia's parliament?", "National Assembly", "House of Commons", "Senate", "Congress", "National Assembly"},
        {"In which year did Zambia gain independence?", "1950", "1964", "1975", "1980", "1964"},
        {"Who was Zambia's first President?", "Rupiah Banda", "Michael Sata", "Frederick Chiluba", "Kenneth Kaunda", "Kenneth Kaunda"},
        {"How many provinces are there in Zambia?", "8", "9", "10", "11", "10"},
        {"Which political party is currently in power in Zambia?", "Patriotic Front", "United Party for National Development", "Movement for Multi-Party Democracy", "United National Independence Party", "United Party for National Development"},
        {"Who was the President of Zambia before Hakainde Hichilema?", "Rupiah Banda", "Michael Sata", "Edgar Lungu", "Levy Mwanawasa", "Edgar Lungu"},
        {"What is the term length for a President in Zambia?", "4 years", "5 years", "6 years", "7 years", "5 years"},
        {"Which Zambian political leader is known as the 'Father of Independence'?", "Simon Kapwepwe", "Harry Nkumbula", "Kenneth Kaunda", "Frederick Chiluba", "Kenneth Kaunda"},
        {"What is the main responsibility of the Zambian Electoral Commission?", "Conducting national elections", "Drafting new laws", "Managing national finances", "Overseeing the judiciary", "Conducting national elections"},
        {"Which city is the administrative capital of Zambia?", "Lusaka", "Livingstone", "Ndola", "Kitwe", "Lusaka"},
        {"Which Zambian president introduced the multi-party democracy system in 1991?", "Kenneth Kaunda", "Frederick Chiluba", "Rupiah Banda", "Michael Sata", "Frederick Chiluba"},
        {"What is the role of the Vice President in Zambia?", "Head of the judiciary", "Head of the National Assembly", "Deputy to the President", "Governor of the Bank of Zambia", "Deputy to the President"},
        {"Who appoints the judges of the Supreme Court in Zambia?", "The National Assembly", "The President", "The Prime Minister", "The Chief Justice", "The President"},
        {"Which document serves as the supreme law of Zambia?", "The Bill of Rights", "The Constitution", "The Penal Code", "The Civil Code", "The Constitution"},
        {"How many members are there in Zambia's National Assembly?", "150", "158", "167", "175", "158"},
        {"Who is the current Speaker of the National Assembly in Zambia?", "Patrick Matibini", "Amusaa Mwanamwambwa", "Nelly Mutti", "John Mutti", "Nelly Mutti"},
        {"What is the minimum age requirement to run for President in Zambia?", "35 years", "40 years", "45 years", "50 years", "35 years"},
        {"Which Zambian President served the longest term?", "Kenneth Kaunda", "Frederick Chiluba", "Levy Mwanawasa", "Edgar Lungu", "Kenneth Kaunda"},
        {"What is the highest court in Zambia?", "Court of Appeal", "Supreme Court", "Constitutional Court", "High Court", "Supreme Court"}
    };

    public PoliticsQuiz() {
        window = new JFrame("Zam Quiz Mania - Politics Quiz");
        window.setSize(900, 650);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
 //ICON
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
        SwingUtilities.invokeLater(() -> new PoliticsQuiz());
    }
}
