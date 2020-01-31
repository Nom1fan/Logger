package com.nom1fan.logger.monitor;

import com.nom1fan.logger.config.MonitorConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;

public class MonitorClientImpl implements MonitorClient {

    private static final String ENDPOINT;

    static {
        try {
            ENDPOINT = MonitorConfiguration.getMonitorServiceEndpoint();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot instantiate monitoring by configuration");
        }
    }

    private final RestTemplate restTemplate;

    public MonitorClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<String> sendAlert(Alert alert) throws URISyntaxException {
        System.out.println("!!!! MONITOR ALERT - " + alert + " !!!!");
//        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUri(new URI(ENDPOINT));
//        UriComponents uriComponents = uriComponentsBuilder.path("/v1/alert/").build();
//        UriComponents encode = uriComponents.encode(StandardCharsets.UTF_8);
//        HttpEntity<Alert> httpEntity = new HttpEntity<>(alert);
//        return restTemplate.exchange(encode.toUri(), HttpMethod.POST, httpEntity, String.class);
        return new ResponseEntity<>(HttpStatus.valueOf(200));
    }

}
