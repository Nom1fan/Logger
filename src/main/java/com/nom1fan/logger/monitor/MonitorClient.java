package com.nom1fan.logger.monitor;

import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public interface MonitorClient {

    ResponseEntity<String> sendAlert(Alert alert) throws MalformedURLException, URISyntaxException;
}
