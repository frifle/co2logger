package de.frifle.co2logger.sensor;

public class MHZ19Exception extends Exception {

    private static final long serialVersionUID = -5512222136322056908L;

    public MHZ19Exception(String message) {
        super(message);
    }

    public MHZ19Exception( String message, Throwable cause ) {
        super(message, cause);
    }
}