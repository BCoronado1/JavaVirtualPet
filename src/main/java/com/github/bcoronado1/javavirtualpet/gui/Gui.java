package com.github.bcoronado1.javavirtualpet.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Class to handle the user interface.
 */
public class Gui {

    private static final String TITLE = "Virtual Pet";
    private JFrame frame = new JFrame(TITLE);
    private JPanel panel = new JPanel(new GridBagLayout());
    private JLabel petNameLabel, dayLabel;
    private JLabel imgLabel = new JLabel();

    private JProgressBar hungerBar = new JProgressBar(0, 100);
    private JProgressBar fatigueBar = new JProgressBar(0, 100);
    private JProgressBar stressBar = new JProgressBar(0, 100);

    private JButton feedButton = new JButton("Feed");
    private JButton restButton = new JButton("Rest");
    private JButton playButton = new JButton("Play");

    public Gui(String petName, ImageIcon initialPetIcon) {
        panel.setBorder(BorderFactory.createEmptyBorder());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        petNameLabel = new JLabel(String.format("Name: %s", petName));
        petNameLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(petNameLabel, gbc);

        dayLabel = new JLabel("Day: 1");
        dayLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(dayLabel, gbc);

        JLabel leftLabel = new JLabel();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(leftLabel, gbc);

        imgLabel.setIcon(initialPetIcon);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(imgLabel, gbc);

        JLabel rightLabel = new JLabel();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(rightLabel, gbc);

        hungerBar.setString("Hunger");
        hungerBar.setValue(0);
        hungerBar.setStringPainted(true);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        panel.add(hungerBar, gbc);

        fatigueBar.setString("Fatigue");
        fatigueBar.setValue(0);
        fatigueBar.setStringPainted(true);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        panel.add(fatigueBar, gbc);

        stressBar.setString("Stress");
        stressBar.setValue(0);
        stressBar.setStringPainted(true);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        panel.add(stressBar, gbc);

        JPanel buttonPanel = new JPanel();
        feedButton.setSize(100, 20);
        buttonPanel.add(feedButton);

        restButton.setSize(100, 20);
        buttonPanel.add(restButton);

        playButton.setSize(100, 20);
        buttonPanel.add(playButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        panel.add(buttonPanel, gbc);

        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
    }

    public JFrame getFrame() {
        return frame;
    }

    public JLabel getDayLabel() {
        return dayLabel;
    }

    public JLabel getImgLabel() {
        return imgLabel;
    }

    public JProgressBar getHungerBar() {
        return hungerBar;
    }

    public JProgressBar getFatigueBar() {
        return fatigueBar;
    }

    public JProgressBar getStressBar() {
        return stressBar;
    }

    public JButton getFeedButton() {
        return feedButton;
    }

    public JButton getRestButton() {
        return restButton;
    }

    public JButton getPlayButton() {
        return playButton;
    }
}
