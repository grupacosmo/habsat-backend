package pl.edu.pk.cosmo.habsatbackend.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Patryk Borchowiec
 * @since 0.0.0
 */
@Data
public class DataFrame {
    private String id;
    private LocalDateTime dateTime;
    private double heightInMeters;
    private double temperatureInCelsius;
    private double longitude;
    private double latitude;

    public DataFrame(LocalDateTime dateTime, double heightInMeters, double temperatureInCelsius, double longitude,
                     double latitude) {
        this.id = UUID.randomUUID().toString();
        this.dateTime = dateTime;
        this.heightInMeters = heightInMeters;
        this.temperatureInCelsius = temperatureInCelsius;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
