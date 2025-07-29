package it.polito.oop.test;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import it.polito.oop.bus.Transport;
import it.polito.oop.bus.Place;

public class TestR3a_Places {

    private static final String POLITECNICO = "Politecnico di Torino";
    private static final String MUNICIPALITY1 = "Torino";
    private static final String MUNICIPALITY2 = "Milano";
    private static final String COMPANY1 = "GTT";
    private static final String COMPANY2 = "ATM";

    private static final String ANGOLO_SU_DUCA = "C.so Stati Uniti angolo C.so Duca";
    private static final String STAZIONE_PN = "Stazione Porta Nuova";
    private static final String LARGO_ORBASSANO = "Largo Orbassano";
    private static final String DUOMO = "Piazza del Duomo";
    private static final String CENTRALE = "Stazione Centrale";

    private Transport t1;
    private Transport t2;
    private Place p1;
    private Place p2;
    private Place p3;
    private Place p4;
    private Place p5;

    @Before
    public void setUp(){
        t1 = new Transport(COMPANY1, MUNICIPALITY1);
        t2 = new Transport(COMPANY2, MUNICIPALITY2);

        p1 = t1.createPlace(POLITECNICO);	
        p2 = t1.createPlace(ANGOLO_SU_DUCA);
        p3 = t1.createPlace(STAZIONE_PN);
        p4 = t1.createPlace(LARGO_ORBASSANO);

        p5 = t2.createPlace(DUOMO);
        t2.createPlace(CENTRALE);
    }

    @Test
    public void testCreatePlace(){
        assertNotNull("Could not create place " + POLITECNICO, p1);
        assertEquals("Wrong place name", POLITECNICO, p1.getName());

        assertNotNull("Could not create place " + DUOMO, p5);
        assertEquals("Wrong place name", DUOMO, p5.getName());
    }
    
    @Test
    public void testGetPlaces(){
        Collection<Place> places1 = t1.getPlaces();
        Collection<Place> places2 = t2.getPlaces();

        assertNotNull("Missing list of places for t1", places1);
        assertNotNull("Missing list of places for t2", places2);

        assertEquals("Some places are missing for t1", 4, places1.size());
        assertEquals("Some places are missing for t2", 2, places2.size());

        assertTrue("Missing place from the list: " + p2.getName(), places1.contains(p2));
        assertTrue("Missing place from the list: " + p4.getName(), places1.contains(p4));
        assertTrue("Missing place from the list: " + p5.getName(), places2.contains(p5));
    }

    @Test
    public void testGetPlace(){		
        Place foundPlace = t1.getPlaces(ANGOLO_SU_DUCA);
        assertNotNull("Missing place", foundPlace);
        assertSame("Different place expected", p2, foundPlace);

        Place foundPlace2 = t2.getPlaces(DUOMO);
        assertNotNull("Missing place", foundPlace2);
        assertSame("Different place expected", p5, foundPlace2);
    }

    @Test
    public void testGetNonExistingPlace(){
        Place notFound = t1.getPlaces("NonExistentPlace");
        assertNull("Non-existent place should return null", notFound);

        Place notFound2 = t2.getPlaces("SomeRandomPlace");
        assertNull("Non-existent place should return null", notFound2);
    }
}
