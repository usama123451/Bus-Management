package it.polito.oop.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

import it.polito.oop.bus.Transport;

public class TestR1a_Transports  {

    private static final String MUNICIPALITY1 = "Torino";
    private static final String MUNICIPALITY2 = "Milano";
    private static final String COMPANY1 = "GTT";
    private static final String COMPANY2 = "ATM";
    
    private Transport t1;
    private Transport t2;

    @Before
    public void setUp(){
        t1 = new Transport(COMPANY1, MUNICIPALITY1);
        t2 = new Transport(COMPANY2, MUNICIPALITY2);
    }

    @Test
    public void testTransport(){
        assertEquals("Wrong company name for t1",
                COMPANY1, t1.getCompany());
        assertEquals("Wrong municipality for t1",
                MUNICIPALITY1, t1.getMunicipality());

        assertEquals("Wrong company name for t2",
                COMPANY2, t2.getCompany());
        assertEquals("Wrong municipality for t2",
                MUNICIPALITY2, t2.getMunicipality());
    }
    
    @Test
    public void testPrice(){
        double price1 = 1.5;
        double price2 = 2.0;
        
        t1.setPrice(price1);
        t2.setPrice(price2);

        assertEquals("Wrong price for t1",
                price1, t1.getPrice(), 0.001);
        assertEquals("Wrong price for t2",
                price2, t2.getPrice(), 0.001);
    }

    @Test
    public void testExtremePrices(){
        double lowPrice = 0.01;  // Smallest valid price
        double highPrice = 100.0; // Arbitrary high but valid price

        t1.setPrice(lowPrice);
        t2.setPrice(highPrice);

        assertEquals("Wrong low price for t1",
                lowPrice, t1.getPrice(), 0.001);
        assertEquals("Wrong high price for t2",
                highPrice, t2.getPrice(), 0.001);
    }

    @Test
    public void testWrongPriceNegative(){
        double price = -2.5;
        assertThrows("-2.5 is not a valid price", 
                    IllegalArgumentException.class, 
                    () -> t1.setPrice(price));
    }

    @Test
    public void testWrongPriceZero(){
        double price = 0;
        assertThrows("Zero is not a valid price", 
                    IllegalArgumentException.class, 
                    () -> t1.setPrice(price));
    }

    @Test
    public void testMultipleInvalidPrices(){
        double[] invalidPrices = {-10.0, -0.01, 0};

        for (double invalidPrice : invalidPrices) {
            assertThrows(invalidPrice + " is not a valid price", 
                        IllegalArgumentException.class, 
                        () -> t2.setPrice(invalidPrice));
        }
    }
}
