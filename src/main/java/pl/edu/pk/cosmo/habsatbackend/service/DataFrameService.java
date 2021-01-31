package pl.edu.pk.cosmo.habsatbackend.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DataFrameService {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public DataFrameService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Scheduled(fixedDelay = 1000)
    public void sendTestFrame() {
        simpMessagingTemplate.convertAndSend("/data/ws","hehe");
    }
}
