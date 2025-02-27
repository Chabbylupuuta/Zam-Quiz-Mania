import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrQuiz {

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
        {"Who is the current President of Zambia as of 2024?", "Hakainde Hichilema", "Edgar Lungu", "Levy Mwanawasa", "Kenneth Kaunda", "Hakainde Hichilema"},
        {"In which year did Hakainde Hichilema assume office as President of Zambia?", "2020", "2021", "2022", "2023", "2021"},
        {"What major economic reforms has Zambia undertaken recently under the new administration?", "Tax cuts for small businesses", "Mining sector reforms", "Subsidies for agricultural exports", "Introduction of universal basic income", "Mining sector reforms"},
        {"Which international organization has recently provided financial aid to Zambia to support its economic recovery?", "World Bank", "International Monetary Fund (IMF)", "African Development Bank (AfDB)", "United Nations Development Programme (UNDP)", "International Monetary Fund (IMF)"},
        {"What is the current inflation rate in Zambia?", "10%", "15%", "20%", "25%", "20%"},
        {"Which major infrastructure project has Zambia recently launched with Chinese investment?", "Construction of a new airport in Lusaka", "Expansion of the Kariba Dam", "Development of a new railway network", "Building of a national sports stadium", "Development of a new railway network"},
        {"What environmental challenge is Zambia currently facing, leading to government initiatives for conservation?", "Deforestation in national parks", "Pollution of major rivers", "Desertification in rural areas", "Coral bleaching in coastal regions", "Deforestation in national parks"},
        {"Which neighboring country has recently signed a bilateral trade agreement with Zambia to boost cross-border commerce?", "Zimbabwe", "Angola", "Tanzania", "Mozambique", "Tanzania"},
        {"What recent healthcare initiative has Zambia launched to improve access to medical services in rural areas?", "Free vaccination program for children", "Construction of new hospitals in urban centers", "Mobile clinics for remote communities", "Training program for healthcare workers", "Mobile clinics for remote communities"},
        {"What diplomatic milestone did Zambia achieve recently in its relations with the European Union?", "Signing of a new trade agreement", "Hosting of an EU-Africa summit", "Establishment of a joint development fund", "Appointment of an EU ambassador to Zambia", "Establishment of a joint development fund"},
        {"How has Zambia addressed recent challenges in its education system, particularly in remote areas?", "Distribution of free textbooks to schools", "Introduction of digital learning platforms", "Recruitment of foreign teachers", "Building of new schools in underserved regions", "Introduction of digital learning platforms"},
        {"What new legislation has Zambia passed to promote gender equality in the workplace?", "Mandatory quotas for female representation in corporate boards", "Equal pay law for men and women in all sectors", "Tax incentives for companies with gender-balanced staff", "Subsidized childcare for working mothers", "Equal pay law for men and women in all sectors"},
        {"Which international summit did Zambia recently host, focusing on climate change and sustainable development?", "United Nations General Assembly", "COP26 (UN Climate Change Conference)", "G20 Summit", "African Union Summit", "African Union Summit"},
        {"How has Zambia's tourism sector adapted to recent global travel restrictions?", "Promotion of domestic tourism campaigns", "Introduction of luxury tourism packages", "Expansion of eco-tourism initiatives", "Partnership with international airlines for direct flights", "Promotion of domestic tourism campaigns"},
        {"What recent technological advancements has Zambia embraced to enhance connectivity and digital inclusion?", "Launch of 5G network in major cities", "Expansion of broadband internet in rural areas", "Introduction of e-government services", "Implementation of a national cybersecurity strategy", "Expansion of broadband internet in rural areas"},
        {"How has Zambia addressed recent concerns over electoral transparency and accountability?", "Establishment of an independent electoral commission", "Introduction of electronic voting systems", "Formation of a national election observation committee", "Implementation of campaign finance reforms", "Establishment of an independent electoral commission"},
        {"Which new trade partnership has Zambia recently joined to expand market access for its agricultural products?", "African Continental Free Trade Area (AfCFTA)", "BRICS trade bloc", "Comprehensive and Progressive Agreement for Trans-Pacific Partnership (CPTPP)", "European Free Trade Association (EFTA)", "African Continental Free Trade Area (AfCFTA)"},
        {"What recent infrastructure development has Zambia prioritized to boost its economic growth?", "Construction of a new international airport", "Upgrading of road networks linking major cities", "Expansion of the national railway system", "Building of renewable energy plants", "Upgrading of road networks linking major cities"},
        {"How has Zambia addressed recent challenges in the energy sector, particularly with regards to electricity supply?", "Introduction of solar energy subsidies", "Construction of new hydroelectric dams", "Implementation of a national energy conservation campaign", "Privatization of state-owned power utilities", "Construction of new hydroelectric dams"},
        {"What recent cultural initiative has Zambia undertaken to promote its heritage and arts internationally?", "Establishment of a national cultural exchange program", "Hosting of an annual international arts festival", "Creation of a digital museum showcasing Zambian history", "Collaboration with UNESCO to protect cultural sites", "Hosting of an annual international arts festival"}
    };

    public CurrQuiz() {
        window = new JFrame("Zam Quiz Mania - Current Affairs of Zambia");
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
private static void openGenreSelector() {
        // Open Genre.java class or any genre selection logic here
        Genre.main(new String[0]); // Assuming Genre.java is your genre selection class
    }
    private static void changeGenre() {
        window.dispose();
      
    }

    public static void main(String[] args) {
        new CurrQuiz();
    }
}
