package pl.edu.pk.cosmo.habsatbackend.model;

import lombok.Data;

import java.util.UUID;

@Data
public class DataFrame {
    private String id;
    private double heightInMeters;
    private double temperatureInCelsius;
    private double longitude;
    private double latitude;

    public DataFrame(double heightInMeters, double temperatureInCelsius, double longitude, double latitude) {
        this.id = UUID.randomUUID().toString();
        this.heightInMeters = heightInMeters;
        this.temperatureInCelsius = temperatureInCelsius;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
