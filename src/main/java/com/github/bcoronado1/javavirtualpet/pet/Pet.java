package com.github.bcoronado1.javavirtualpet.pet;

import com.github.bcoronado1.javavirtualpet.util.Config;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * The pet class defines the pet's state. It establishes the pet's attributes and image as shown in the user
 * interface.
 */
public class Pet {

    private String name;
    private Double hunger = 0.0;
    private Double fatigue = 0.0;
    private Double stress = 0.0;
    private ImageIcon happyIcon, unimpressedIcon, distressedIcon, sadIcon, deadIcon;

    public Pet(String name) {
        this.name = name;
        happyIcon = loadImage("images/happy.png");
        unimpressedIcon = loadImage("images/unimpressed.png");
        distressedIcon = loadImage("images/distressed.png");
        sadIcon = loadImage("images/sad.png");
        deadIcon = loadImage("images/dead.png");
    }

    public String getName() {
        return name;
    }

    public Double getHunger() {
        return hunger;
    }

    public Double getFatigue() {
        return fatigue;
    }

    public Double getStress() {
        return stress;
    }

    public void feed() {
        hunger -= Config.getPetPointsPerFeed();
        if (hunger < 0.0) {
            hunger = 0.0;
        }
    }

    public void rest() {
        fatigue -= Config.getPetPointsPerRest();
        if (fatigue < 0.0) {
            fatigue = 0.0;
        }
    }

    public void play() {
        stress -= Config.getPetPointsPerPlay();
        if (stress < 0.0) {
            stress = 0.0;
        }
    }

    public boolean isAlive() {
        if (hunger >= 100.0 || fatigue >= 100.0 || stress >= 100.0) {
            return false;
        }
        return true;
    }

    public void step() {
        hunger += Config.getPetHungerIncreasePerStep();
        fatigue += Config.getPetFatigueIncreasePerStep();
        stress += Config.getPetStressIncreasePerStep();
    }

    private ImageIcon loadImage(String resourcePath) {
        ImageIcon image = null;
        try {
            InputStream inputStream = getClass()
                    .getClassLoader().getResourceAsStream(resourcePath);
            if (inputStream != null) {
                image = new ImageIcon(ImageIO.read(inputStream));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public ImageIcon getIcon() {
        if (!isAlive()) {
            return deadIcon;
        } else if (hunger > 75.0 || fatigue > 75.0 || stress > 75.0) {
            return sadIcon;
        } else if (hunger > 50.0 || fatigue > 50.0 || stress > 50.0) {
            return distressedIcon;
        } else if (hunger > 25.0 || fatigue > 25.0 || stress > 25.0) {
            return unimpressedIcon;
        }
        return happyIcon;
    }
}
