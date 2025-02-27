import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SportsQuiz {

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
        {"What is the national football team of Zambia commonly known as?", "The Chipolopolo", "The Warriors", "The Lions", "The Eagles", "The Chipolopolo"},
        {"In which year did Zambia win the Africa Cup of Nations (AFCON) for the first time?", "1984", "1996", "2012", "2015", "2012"},
        {"Who scored the winning goal for Zambia in the 2012 AFCON final?", "Christopher Katongo", "Rainford Kalaba", "Stoppila Sunzu", "Emmanuel Mayuka", "Stoppila Sunzu"},
        {"Which Zambian footballer has played for the English Premier League club Leicester City?", "Collins Mbesuma", "Rainford Kalaba", "Patson Daka", "Stoppila Sunzu", "Patson Daka"},
        {"Who is the most capped player in the history of the Zambian national football team?", "Kalusha Bwalya", "Christopher Katongo", "Kennedy Mweene", "Rainford Kalaba", "Kennedy Mweene"},
        {"Which Zambian footballer won the African Footballer of the Year award in 1988?", "Kalusha Bwalya", "Charles Musonda", "Godfrey Chitalu", "Dennis Lota", "Kalusha Bwalya"},
        {"What tragic event occurred to the Zambian national football team in 1993?", "Plane crash off the coast of Gabon", "Stadium collapse in Lusaka", "Bus accident on the way to a match", "Hotel fire during a tournament", "Plane crash off the coast of Gabon"},
        {"Which Zambian athlete won a bronze medal at the 1984 Summer Olympics in Los Angeles?", "Samuel Matete", "Kennedy Luchembe", "Lottie Mwale", "Keith Mwila", "Keith Mwila"},
        {"What sport is Samuel Matete known for?", "Boxing", "Football", "400m hurdles", "Long jump", "400m hurdles"},
        {"Which city hosted the All-Africa Games where Zambia's football team won the gold medal in 1987?", "Nairobi", "Harare", "Lusaka", "Algiers", "Nairobi"},
        {"In which year did Zambia host the COSAFA Cup?", "2003", "2007", "2013", "2019", "2013"},
        {"Which Zambian boxer is known for his notable fights in the 1970s and 1980s?", "Joseph Chingangu", "Lottie Mwale", "Kennedy Kanyanta", "Catherine Phiri", "Lottie Mwale"},
        {"Who was the captain of the Zambian national football team during their 2012 AFCON victory?", "Rainford Kalaba", "Kennedy Mweene", "Christopher Katongo", "Isaac Chansa", "Christopher Katongo"},
        {"What is the primary sport played at the Levy Mwanawasa Stadium?", "Rugby", "Cricket", "Football", "Athletics", "Football"},
        {"Which Zambian football club has won the most Zambian Premier League titles?", "ZESCO United", "Nkana FC", "Power Dynamos", "Green Buffaloes", "Nkana FC"},
        {"Which Zambian female athlete has won a major international title in boxing?", "Barbara Banda", "Esther Phiri", "Hellen Wenda", "Jennifer Chipo", "Esther Phiri"},
        {"In which year did Zambian footballer Patson Daka join Leicester City?", "2018", "2019", "2020", "2021", "2021"},
        {"Which Zambian swimmer represented the country at the 2016 Summer Olympics in Rio?", "James Chansa", "Shaquille Moosa", "Tilka Paljk", "Jade Howard", "Jade Howard"},
        {"Who was the Zambian coach during the 2012 AFCON tournament?", "Patrice Beaumelle", "Wedson Nyirenda", "Herve Renard", "Milutin Sredojević", "Herve Renard"},
        {"Which Zambian footballer played for the Spanish club Villarreal?", "Emmanuel Mayuka", "Stopilla Sunzu", "Rainford Kalaba", "Charly Musonda", "Charly Musonda"}
    };

    public SportsQuiz() {
        window = new JFrame("Zam Quiz Mania - Sports in Zambia");
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
            openGenreSelector();
          
            window.dispose();
        }
    }private static void openGenreSelector() {
        // Open Genre.java class or any genre selection logic here
        Genre.main(new String[0]); // Assuming Genre.java is your genre selection class
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SportsQuiz());
    }
}
