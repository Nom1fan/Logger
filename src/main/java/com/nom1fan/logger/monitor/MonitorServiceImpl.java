package com.nom1fan.logger.monitor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class MonitorServiceImpl implements MonitorService {

    private final MonitorClient monitorClient;

    public MonitorServiceImpl(MonitorClient monitorClient) {
        this.monitorClient = monitorClient;
    }

    @Override
    public void sendAlert(Alert alert) {
        try {
            ResponseEntity<String> responseEntity = monitorClient.sendAlert(alert);
            HttpStatus statusCode = responseEntity.getStatusCode();
            if (!statusCode.is2xxSuccessful()) {
                throw new RuntimeException("Failed to alert monitor service:" + statusCode);
            }
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
