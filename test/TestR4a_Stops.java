package it.polito.oop.test;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import it.polito.oop.bus.Transport;
import it.polito.oop.bus.Route;
import it.polito.oop.bus.Place;

public class TestR4a_Stops {
    private Transport t1;
    private Transport t2;
    private Place poli;
    private Place angoloSU;
    private Place pn;
    private Place largoOrb;
    private Place duomo;
    private Place centrale;
    private Route l1;
    private Route l33;
    private Route lM1;

    @Before
    public void setUp(){
        t1 = new Transport("GTT", "Torino");
        t2 = new Transport("ATM", "Milano");

        poli = t1.createPlace("Politecnico");
        angoloSU = t1.createPlace("C.so Stati Uniti angolo C.so Duca");
        pn = t1.createPlace("Stazione Porta Nuova");
        largoOrb = t1.createPlace("Largo Orbassano");

        duomo = t2.createPlace("Piazza del Duomo");
        centrale = t2.createPlace("Stazione Centrale");

        l1 = t1.createRoute("1", true, false);
        l33 = t1.createRoute("33", true, true);
        lM1 = t2.createRoute("M1", true, true);

        assertNotNull("Could not create places", poli);
        assertNotNull("Could not create routes", l1);
    }

    @Test
    public void testAddStop(){
        l33.addStop(pn);
        l33.addStop(angoloSU);
        l33.addStop(poli);
        l33.addStop(largoOrb);

        Collection<Place> stops = l33.getStops();

        assertNotNull("No stops for line " + l33.getName(), stops);
        assertEquals("Some stops are missing", 4, stops.size());
        assertTrue("Stops do not include " + angoloSU.getName(), stops.contains(angoloSU));
        assertTrue("Stops do not include " + largoOrb.getName(), stops.contains(largoOrb));
    }

    @Test
    public void testAddStopsDifferentOrder(){
        lM1.addStop(centrale);
        lM1.addStop(duomo);

        Collection<Place> stops = lM1.getStops();

        assertNotNull("No stops for line " + lM1.getName(), stops);
        assertEquals("Some stops are missing", 2, stops.size());
        assertTrue("Stops do not include " + duomo.getName(), stops.contains(duomo));
        assertTrue("Stops do not include " + centrale.getName(), stops.contains(centrale));
    }

    @Test
    public void testFirstTerminal(){
        l1.addStop(pn);
        l1.addStop(angoloSU);
        l1.addStop(poli);

        assertSame("Wrong first terminal", pn, l1.getFirstTerminal());
    }

    @Test
    public void testFirstTerminalDifferentOrder(){
        l33.addStop(largoOrb);
        l33.addStop(poli);
        l33.addStop(angoloSU);
        l33.addStop(pn);

        assertSame("Wrong first terminal", largoOrb, l33.getFirstTerminal());
    }

    @Test
    public void testLastTerminal(){
        l1.addStop(pn);
        l1.addStop(angoloSU);
        l1.addStop(poli);

        assertSame("Wrong last terminal", poli, l1.getLastTerminal());
    }

    @Test
    public void testLastTerminalDifferentOrder(){
        lM1.addStop(duomo);
        lM1.addStop(centrale);

        assertSame("Wrong last terminal", centrale, lM1.getLastTerminal());
    }
}
