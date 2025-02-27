import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome {
    public static void main(String[] args) {
        final int WINDOW_WIDTH = 1000; 
        final int WINDOW_HEIGHT = 850;

        JFrame window = new JFrame("Zam Quiz Mania!");

        ImageIcon imagez = new ImageIcon("Zam Mania Quiz .png");
        window.setIconImage(imagez.getImage());

        window.getContentPane().setBackground(new Color(65, 105, 225));
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());

        // Text part
        JLabel label = new JLabel("WELCOME TO ZAM QUIZ MANIA");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM); // This positions the text at the top
        label.setHorizontalTextPosition(JLabel.CENTER); // This centers the text horizontally
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Headings", Font.BOLD, 40));

        // Picture
        ImageIcon image1 = new ImageIcon("Zam Mania Quiz .png");
        label.setIcon(image1);

        // Create a panel for the label and button
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(65, 105, 225));

        // Add padding to the center panel
        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Add label to the center panel
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        centerPanel.add(label);

        // Create the start button
        JButton startButton = new JButton("START");
        startButton.setFont(new Font("Arial", Font.BOLD, 30));
        startButton.setPreferredSize(new Dimension(200, 80));
        startButton.setBackground(new Color(155, 15, 90));
        startButton.setForeground(Color.BLACK);
        startButton.setFocusPainted(false);
        startButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

        // Create the Practice button
        JButton practiceButton = new JButton("PRACTICE");
        practiceButton.setFont(new Font("Arial", Font.BOLD, 30));
        practiceButton.setPreferredSize(new Dimension(200, 80));
        practiceButton.setBackground(new Color(155, 15, 90));
        practiceButton.setForeground(Color.BLACK);
        practiceButton.setFocusPainted(false);
        practiceButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

        // Create the Option button
        JButton optionButton = new JButton("OPTIONS");
        optionButton.setFont(new Font("Arial", Font.BOLD, 30));
        optionButton.setPreferredSize(new Dimension(200, 80));
        optionButton.setBackground(new Color(155, 15, 90));
        optionButton.setForeground(Color.BLACK);
        optionButton.setFocusPainted(false);
        optionButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

        // Add ActionListener to start button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Genre class here
                Genre.main(new String[0]); // Launch the Genre class
                window.dispose(); // Close the welcome window
            }
        });

        // Add ActionListener to practice button
        practiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Practice class here
                Practice.main(new String[0]); // Launch the Practice class
                window.dispose(); // Close the welcome window
            }
        });

        // Add ActionListener to option button
        optionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Options class here
                Options.main(new String[0]); // Launch the Options class
                window.dispose(); // Close the welcome window
            }
        });

        // Add some spacing between the label and the buttons
        centerPanel.add(Box.createVerticalStrut(30));

        // Add the start, practice, and option buttons to the center panel
        centerPanel.add(startButton);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(practiceButton);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(optionButton);

        // Add center panel to the window
        window.add(centerPanel, BorderLayout.CENTER);

        window.setVisible(true);
    }
}
