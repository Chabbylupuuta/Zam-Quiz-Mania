import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Genre {
    private static final int WINDOW_WIDTH = 900;
    private static final int WINDOW_HEIGHT = 650;

    public static void main(String[] args) {
        createAndShowGUI();
    }

    private static void createAndShowGUI() {
        JFrame window = new JFrame("Zam Quiz Mania!");
        window.setIconImage(new ImageIcon("Zam Mania Quiz.png").getImage());
        window.getContentPane().setBackground(new Color(65, 105, 225));
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 //ICON
        ImageIcon imagez = new ImageIcon("Zam Mania Quiz .png");
        window.setIconImage(imagez.getImage());
        
        JLabel genreQuestion = createGenreQuestion();
        JPanel questionPanel = createQuestionPanel(genreQuestion);

        JPanel genresPanel = createGenresPanel();
        window.add(questionPanel, BorderLayout.NORTH);
        window.add(genresPanel, BorderLayout.WEST);

        window.setVisible(true);
    }

    private static JLabel createGenreQuestion() {
        JLabel genreQuestion = new JLabel("What genre would you like to play?");
        genreQuestion.setFont(new Font("Times New Roman", Font.BOLD, 32));
        genreQuestion.setForeground(Color.WHITE);
        genreQuestion.setHorizontalAlignment(JLabel.CENTER);
        return genreQuestion;
    }

    private static JPanel createQuestionPanel(JLabel genreQuestion) {
        JPanel questionPanel = new JPanel();
        questionPanel.setBackground(new Color(65, 105, 225));
        questionPanel.add(genreQuestion);
        return questionPanel;
    }

    private static JPanel createGenresPanel() {
        JPanel genresPanel = new JPanel();
        genresPanel.setLayout(new BoxLayout(genresPanel, BoxLayout.Y_AXIS));
        genresPanel.setBackground(new Color(65, 105, 225));
        genresPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] genres = {"POLITICS", "HISTORY", "CURRENT AFFAIRS", "TOURISM", "SPORTS", "GEOGRAPHY", "MUSIC"};
        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        Color buttonColor = new Color(155, 15, 90);

        for (String genre : genres) {
            JButton button = createButton(genre, buttonFont, buttonColor);
            genresPanel.add(button);
            genresPanel.add(Box.createVerticalStrut(10));
        }

        return genresPanel;
    }

    private static JButton createButton(String genre, Font font, Color color) {
        JButton button = new JButton(genre);
        button.setFont(font);
        button.setForeground(Color.BLACK);
        button.setBackground(color);
        button.setAlignmentX(JButton.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(300, 50));
        button.setFocusPainted(false);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGenreSelection(genre, e);
            }
        });

        return button;
    }

   private static void handleGenreSelection(String genre, ActionEvent e) {
    JFrame window = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
    window.dispose();

    switch (genre) {
        case "POLITICS":
            new PoliticsQuiz();
            break;
        case "HISTORY":
            new HistoryQuiz();
            break;
        case "SPORTS": // Corrected typo
            new SportsQuiz();
            break;
        case "TOURISM":
            new TourQuiz();
            break;
        case "GEOGRAPHY":
            new GeoQuiz();
            break;
        case "CURRENT AFFAIRS":
            new CurrQuiz();
            break;
        case "MUSIC":
            new MusicQuiz();
            break;
        // Add more cases for other genres
        default:
            System.out.println("Unknown genre: " + genre);
    }
}

}
