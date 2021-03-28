package pl.edu.pk.cosmo.habsatbackend.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.edu.pk.cosmo.habsatbackend.model.DataFrame;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Patryk Borchowiec
 * @since 0.0.0
 */
@Service
public class DataFrameService {
    private final SimpMessagingTemplate simpMessagingTemplate;

    // todo dummy variables
    private DataFrame currentDataFrame;
    private static final int DELAY_IN_SEC = 5;
    private static int FRAMES_LIMIT = 100;
    private static final List<DataFrame> dataFrames = new LinkedList<>();

    public DataFrameService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.currentDataFrame = new DataFrame(LocalDateTime.now(), 1000, 20, 20,
                50);
    }

    @Scheduled(fixedDelay = 1000 * DELAY_IN_SEC)
    public void sendTestFrame() {
        DataFrame dummyDataFrame = getDummyDataFrame();
        currentDataFrame = dummyDataFrame;

        if (dataFrames.size() > FRAMES_LIMIT) {
            dataFrames.clear();
        }

        dataFrames.add(dummyDataFrame);
        simpMessagingTemplate.convertAndSend("/data/ws", dummyDataFrame);
    }

    // todo dummy function
    public DataFrame getDummyDataFrame() {
        Random random = new Random();

        DataFrame dataFrame = new DataFrame(
                LocalDateTime.now(),
                currentDataFrame.getHeightInMeters() + random.nextDouble() * 2 * DELAY_IN_SEC,
                20 + random.nextDouble(),
                currentDataFrame.getLongitude() + random.nextDouble() * DELAY_IN_SEC,
                currentDataFrame.getLatitude() + random.nextDouble() * DELAY_IN_SEC
        );

        if (dataFrame.getLatitude() > 90) {
            dataFrame.setLatitude(dataFrame.getLatitude() - 180);
        }

        if (dataFrame.getLongitude() > 180) {
            dataFrame.setLongitude(dataFrame.getLongitude() - 360);
        }

        if (dataFrame.getHeightInMeters() > 30000) {
            dataFrame.setHeightInMeters(1000);
        }

        return dataFrame;
    }

    public List<DataFrame> getDataFrames() {
        // deep copy
        return dataFrames.stream()
                .map(dataFrame -> {
                    try {
                        return dataFrame.clone();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
