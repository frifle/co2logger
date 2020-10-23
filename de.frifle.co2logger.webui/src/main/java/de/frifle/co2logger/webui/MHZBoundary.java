package de.frifle.co2logger.webui;


import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import de.frifle.co2logger.sensor.*;

@Singleton
public class MHZBoundary {
    
    @ConfigProperty
    private String defaultCommPort;
    private MHZ19Sensor sensor;

    @PostConstruct
    private void init() {

    }

}