package com.github.bcoronado1.javavirtualpet;

import com.github.bcoronado1.javavirtualpet.simulation.Sim;

/**
 * The VirtualPetSimLauncher is the entry point for the Java Virtual Pet application.
 */
public class VirtualPetSimLauncher {
    public static void main(String[] args) {
        Sim sim = new Sim();
        sim.start();
    }
}