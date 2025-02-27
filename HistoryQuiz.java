import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistoryQuiz {
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
        {"When did Zambia gain independence from British colonial rule?", "1945", "1950", "1964", "1970", "1964"},
        {"Who was the first President of Zambia?", "Frederick Chiluba", "Levy Mwanawasa", "Kenneth Kaunda", "Rupiah Banda", "Kenneth Kaunda"},
        {"What was the former name of Zambia before independence?", "Rhodesia", "Northern Rhodesia", "Nyasaland", "Bechuanaland", "Northern Rhodesia"},
        {"Which Zambian leader is known as the \"Father of the Nation\"?", "Simon Kapwepwe", "Kenneth Kaunda", "Harry Nkumbula", "Levy Mwanawasa", "Kenneth Kaunda"},
        {"Which political party led Zambia to independence?", "United National Independence Party (UNIP)", "Patriotic Front (PF)", "Movement for Multi-Party Democracy (MMD)", "Zambia Congress Party (ZCP)", "United National Independence Party (UNIP)"},
        {"What significant event happened in Zambia in 1991?", "Gained independence", "First multi-party elections", "Kenneth Kaunda became President", "Levy Mwanawasa's death", "First multi-party elections"},
        {"Who succeeded Kenneth Kaunda as President of Zambia in 1991?", "Rupiah Banda", "Michael Sata", "Frederick Chiluba", "Levy Mwanawasa", "Frederick Chiluba"},
        {"Which Zambian President is known for introducing economic reforms in the 1990s?", "Kenneth Kaunda", "Frederick Chiluba", "Levy Mwanawasa", "Michael Sata", "Frederick Chiluba"},
        {"What major policy did Kenneth Kaunda implement during his presidency?", "Privatization of state-owned enterprises", "Nationalization of key industries", "Introduction of multi-party democracy", "Deregulation of the economy", "Nationalization of key industries"},
        {"When did Zambia become a one-party state under UNIP?", "1968", "1972", "1980", "1986", "1972"},
        {"Who was Zambia's President during the transition to multi-party democracy in 1991?", "Kenneth Kaunda", "Frederick Chiluba", "Rupiah Banda", "Michael Sata", "Kenneth Kaunda"},
        {"Which Zambian President died in office in 2008?", "Frederick Chiluba", "Levy Mwanawasa", "Michael Sata", "Kenneth Kaunda", "Levy Mwanawasa"},
        {"What was the main reason for the Federation of Rhodesia and Nyasaland's dissolution in 1963?", "Economic crisis", "Political pressure from nationalist movements", "International sanctions", "Natural disasters", "Political pressure from nationalist movements"},
        {"Who was the leader of the Zambian African National Congress (ZANC) before its dissolution?", "Kenneth Kaunda", "Simon Kapwepwe", "Harry Nkumbula", "Godwin Lewanika", "Kenneth Kaunda"},
        {"What role did Zambia play in the liberation struggles of Southern Africa?", "Supported liberation movements", "Remained neutral", "Opposed liberation movements", "Became a base for colonial powers", "Supported liberation movements"},
        {"Which significant economic resource was nationalized in Zambia in the early 1970s?", "Oil", "Copper mines", "Diamond mines", "Timber", "Copper mines"},
        {"Who was the Zambian President during the introduction of the Structural Adjustment Program (SAP) in the 1980s?", "Kenneth Kaunda", "Frederick Chiluba", "Levy Mwanawasa", "Rupiah Banda", "Kenneth Kaunda"},
        {"In which year did Zambia host the Organization of African Unity (OAU) summit?", "1964", "1970", "1973", "1979", "1979"},
        {"What was the primary reason for the economic decline in Zambia during the late 20th century?", "Droughts", "Decline in copper prices", "Political instability", "International sanctions", "Decline in copper prices"},
        {"Which event marked the end of Kenneth Kaunda's presidency?", "His resignation", "A military coup", "Losing the 1991 elections", "His death", "Losing the 1991 elections"}
    };

    public HistoryQuiz() {
        window = new JFrame("Zam Quiz Mania");
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
        SwingUtilities.invokeLater(() -> new HistoryQuiz());
    }
}
