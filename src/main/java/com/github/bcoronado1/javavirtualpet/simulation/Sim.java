package com.github.bcoronado1.javavirtualpet.simulation;

import com.github.bcoronado1.javavirtualpet.gui.Gui;
import com.github.bcoronado1.javavirtualpet.pet.Pet;
import com.github.bcoronado1.javavirtualpet.util.Config;
import com.google.gson.Gson;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * The Sim class handles events and actions in the simulation environment. Application behavior is guided by a main
 * control loop to handle pet environment state and update the user interface. Socket messaging is enabled to
 * interact with the simulation through requests.
 */
public class Sim {

    private Pet pet;
    private Gui gui;
    private AtomicLong day = new AtomicLong(1);
    private AtomicLong currentStep = new AtomicLong();
    private Thread mainControlLoopThread = new Thread(this::mainControlLoop);
    private Thread serviceThread = new Thread(this::serviceListener);

    private ZContext context = new ZContext();
    private ZMQ.Socket repSocket = context.createSocket(SocketType.REP);

    public Sim() {
        pet = new Pet(Config.getPetName());
        gui = new Gui(pet.getName(), pet.getIcon());
        gui.getFeedButton().addActionListener(feedListener);
        gui.getRestButton().addActionListener(restListener);
        gui.getPlayButton().addActionListener(playListener);
        setupService();
    }

    public void start() {
        mainControlLoopThread.start();
        serviceThread.start();
    }

    /**
     * Bind the ZMQ address to serve requests.
     */
    private void setupService() {
        repSocket.bind(String.format("tcp://%s", Config.getServiceAddress()));
    }

    /**
     * Go through the simulation loop by updating the pet environment and user interface at the configured interval.
     */
    private void mainControlLoop() {
        while (this.pet.isAlive()) {
            try {
                pet.step();
                updateGui();
                Thread.sleep(Config.getSimStepIntervalMilliseconds());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Serve the request based on requester's interest.
     * "state" will send back the pet's current state.
     * "feed" will simulate pressing the Feed button.
     * "rest" will simulate pressing the Rest button.
     * "play" will simulate pressing the Play button.
     */
    private void serviceListener() {
        while (Thread.currentThread().isAlive()) {
            byte[] topicBytes = repSocket.recv();
            String topicString = new String(topicBytes);

            switch (topicString) {
                case "state":
                    Map<String, Object> state = new HashMap<>();
                    state.put("name", pet.getName());
                    state.put("hunger", pet.getHunger());
                    state.put("fatigue", pet.getFatigue());
                    state.put("stress", pet.getStress());
                    repSocket.send(new Gson().toJson(state));
                    break;
                case "feed":
                    pet.feed();
                    repSocket.send("OK".getBytes());
                    break;
                case "rest":
                    pet.rest();
                    repSocket.send("OK".getBytes());
                    break;
                case "play":
                    pet.play();
                    repSocket.send("OK".getBytes());
                    break;
                default:
                    System.out.println(String.format("Unknown service topic %s", topicString));
            }
        }
    }

    /**
     * This method propagates current pet environment state to the user interface.
     */
    private void updateGui() {
        Long step = currentStep.incrementAndGet();
        if (new Long(0).equals(step % Config.getSimStepsInDay())) {
            day.incrementAndGet();
            gui.getDayLabel().setText(String.format("Day: %s", day.toString()));
        }
        gui.getHungerBar().setValue(pet.getHunger().intValue());
        gui.getFatigueBar().setValue(pet.getFatigue().intValue());
        gui.getStressBar().setValue(pet.getStress().intValue());
        gui.getImgLabel().setIcon(pet.getIcon());
        gui.getFrame().repaint();
    }

    /**
     * Listeners to be applied to user interface buttons.
     */
    ActionListener feedListener = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            pet.feed();
        }
    };

    ActionListener restListener = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            pet.rest();
        }
    };

    ActionListener playListener = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            pet.play();
        }
    };
}
