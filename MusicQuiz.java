import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicQuiz {

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
        {"Who is considered the 'King of Zambian music'?", "Macky 2", "Danny Kaya", "Paul Ngozi", "Slapdee", "Paul Ngozi"},
        {"What is the traditional music genre of the Lozi people in Zambia?", "Kalindula", "Maoma", "Zambezi Blues", "Kwela", "Maoma"},
        {"Which Zambian artist won the AFRIMA award for Best Male Artist in Southern Africa in 2019?", "Yo Maps", "Chef 187", "Roberto", "Slapdee", "Slapdee"},
        {"What is the name of the famous Zambian music festival that celebrates local and international artists?", "Stanbic Music Festival", "Lusaka Music Festival", "ZedFest", "Copperbelt Music Carnival", "Stanbic Music Festival"},
        {"Which Zambian musician is known for the hit song 'Amakebo'?", "Dalisoul", "General Ozzy", "Mampi", "Petersen Zagaze", "Dalisoul"},
        {"What music genre is closely associated with the late Zambian artist P.K. Chishala?", "Kalindula", "Zamrock", "Kwasa Kwasa", "Rumba", "Kalindula"},
        {"Who is the lead vocalist of the Zambian band 'Witch'?", "Paul Ngozi", "Jagari Chanda", "Emmanuel Mulemena", "Joe Chibangu", "Jagari Chanda"},
        {"Which Zambian female artist is known for the song 'Walilowelela'?", "Mampi", "Cleo Ice Queen", "Kantu", "Wezi", "Wezi"},
        {"What is the name of the Zambian music award that recognizes excellence in the local music industry?", "Zambian Music Awards (ZMAs)", "Zed Music Awards", "Ngoma Awards", "Kalindula Awards", "Ngoma Awards"},
        {"Which Zambian artist collaborated with South African DJ Maphorisa on the song 'I Got You'?", "Yo Maps", "Roberto", "Macky 2", "Chef 187", "Roberto"},
        {"Who is the founder of the legendary Zambian music group 'Ricky Banda and The Lusaka Rhythms'?", "Ricky Banda", "Paul Ngozi", "Emmanuel Mulemena", "Ackim Simukonda", "Ricky Banda"},
        {"What instrument is commonly used in traditional Zambian music?", "Mbira", "Marimba", "Kalimba", "Ngoma drum", "Ngoma drum"},
        {"Which Zambian musician is known for the popular song 'Take My Heart'?", "Roberto", "Exile (Izrael)", "Petersen Zagaze", "T-Sean", "Exile (Izrael)"},
        {"What style of music did the Zambian band 'Mosi-O-Tunya' primarily play?", "Zamrock", "Kalindula", "Afrobeat", "Kwasa Kwasa", "Zamrock"},
        {"Who is the Zambian gospel artist known for the song 'Chikondi'?", "Pompi", "Ephraim", "Suwilanji", "Rachael Nanyangwe", "Ephraim"},
        {"Which Zambian artist's album 'Am In Asia' received international acclaim?", "B Flow", "Macky 2", "Slapdee", "Chef 187", "B Flow"},
        {"What is the name of the Zambian music genre that blends traditional sounds with modern beats?", "Zambeat", "Zambezi Jazz", "Zamrock", "Zed Beats", "Zambeat"},
        {"Who is the Zambian artist behind the hit song 'Kumwesu'?", "Afunika", "T-Sean", "Yo Maps", "Chanda Mbao", "Afunika"},
        {"What music award did the Zambian artist Cleo Ice Queen win in 2015?", "Best Female Artist in Southern Africa at the AFRIMA", "Best Newcomer at the Channel O Music Video Awards", "Best Collaboration at the Zambian Music Awards", "Best Hip Hop Act at the Ngoma Awards", "Best Female Artist in Southern Africa at the AFRIMA"},
        {"Which Zambian artist is known for their philanthropic work and social activism through music?", "B Flow", "Macky 2", "Slapdee", "Pilato", "B Flow"}
    };

    public MusicQuiz() {
        window = new JFrame("Zam Quiz Mania - Zambian Music");
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
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Restart", "Change Genre"}, null);

        if (choice == JOptionPane.YES_OPTION) {
            restartQuiz();
        } else {
            openGenreSelector();
            changeGenre();
        }
    }

    private static void restartQuiz() {
        currentQuestionIndex = 0;
        score = 0;
        timeRemaining = 90;
        loadQuestion();
        timer.start();
    }

    private static void changeGenre() {
        window.dispose();
    }
private static void openGenreSelector() {
        // Open Genre.java class or any genre selection logic here
        Genre.main(new String[0]); // Assuming Genre.java is your genre selection class
    }
    public static void main(String[] args) {
        new MusicQuiz();
    }
}
