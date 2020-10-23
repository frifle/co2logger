package de.frifle.co2logger.sensor;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class CRCCheckTest {
    
    private byte[] readCo2ValueResponse = { (byte) 0xff, (byte) 0x01, (byte) 0x86, (byte) 0x00, (byte) 0x00, (byte) 0x00,
        (byte) 0x00, (byte) 0x00};

        

    @Test
    public void testCalculateCRC(){

        byte crc = CRCCheck.calculateCRC( readCo2ValueResponse );

        assertEquals((byte)0x79, crc);
    } 
}

