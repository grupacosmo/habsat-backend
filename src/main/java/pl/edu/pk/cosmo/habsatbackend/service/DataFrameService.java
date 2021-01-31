package pl.edu.pk.cosmo.habsatbackend.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.edu.pk.cosmo.habsatbackend.model.DataFrame;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class DataFrameService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private DataFrame currentDataFrame;
    private static final int DELAY_IN_SEC = 5;

    public DataFrameService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.currentDataFrame = new DataFrame(LocalDateTime.now(), 1000, 20, 20,
                50);
    }

    @Scheduled(fixedDelay = 1000 * DELAY_IN_SEC)
    public void sendTestFrame() {
        Random random = new Random();

        currentDataFrame = new DataFrame(
                LocalDateTime.now(),
                currentDataFrame.getHeightInMeters() + random.nextDouble() * 2 * DELAY_IN_SEC,
                20 + random.nextDouble(),
                currentDataFrame.getLongitude() + random.nextDouble() * DELAY_IN_SEC,
                currentDataFrame.getLatitude() + random.nextDouble() * DELAY_IN_SEC
        );

        if (currentDataFrame.getLatitude() > 90) {
            currentDataFrame.setLatitude(currentDataFrame.getLatitude() - 180);
        }

        if (currentDataFrame.getLongitude() > 180) {
            currentDataFrame.setLongitude(currentDataFrame.getLongitude() - 360);
        }

        if (currentDataFrame.getHeightInMeters() > 30000) {
            currentDataFrame.setHeightInMeters(1000);
        }

        simpMessagingTemplate.convertAndSend("/data/ws",currentDataFrame);
    }
}
