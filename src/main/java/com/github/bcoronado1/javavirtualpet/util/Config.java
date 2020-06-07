package com.github.bcoronado1.javavirtualpet.util;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Config is a custom configuration class to serialize application settings into a singleton.
 */
public class Config {

    private static final String CONFIG_FILEPATH = "config.json";
    private static final Config INSTANCE;

    Long simStepIntervalMilliseconds;
    Integer simStepsInDay;
    Double petHungerIncreasePerStep, petFatigueIncreasePerStep, petStressIncreasePerStep, petPointsPerFeed, petPointsPerRest, petPointsPerPlay;
    String petName, serviceAddress;

    static {
        Gson gson = new Gson();
        InputStream inputStream = Config.class.getClassLoader().getResourceAsStream(CONFIG_FILEPATH);
        Reader reader = new InputStreamReader(inputStream);
        INSTANCE = gson.fromJson(reader, Config.class);
    }

    private Config() {
    }

    public static Long getSimStepIntervalMilliseconds() {
        return INSTANCE.simStepIntervalMilliseconds;
    }

    public static Integer getSimStepsInDay() {
        return INSTANCE.simStepsInDay;
    }

    public static Double getPetHungerIncreasePerStep() {
        return INSTANCE.petHungerIncreasePerStep;
    }

    public static Double getPetFatigueIncreasePerStep() {
        return INSTANCE.petFatigueIncreasePerStep;
    }

    public static Double getPetStressIncreasePerStep() {
        return INSTANCE.petStressIncreasePerStep;
    }

    public static Double getPetPointsPerFeed() {
        return INSTANCE.petPointsPerFeed;
    }

    public static Double getPetPointsPerRest() {
        return INSTANCE.petPointsPerRest;
    }

    public static Double getPetPointsPerPlay() {
        return INSTANCE.petPointsPerPlay;
    }

    public static String getPetName() {
        return INSTANCE.petName;
    }

    public static String getServiceAddress() {
        return INSTANCE.serviceAddress;
    }
}
