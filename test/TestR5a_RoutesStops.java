package it.polito.oop.test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import it.polito.oop.bus.Transport;
import it.polito.oop.bus.Route;
import it.polito.oop.bus.Place;

public class TestR5a_RoutesStops {
    private Transport t1;
    private Transport t2;
    private Place polito;
    private Place angoloSU;
    private Place portaNuova;
    private Place largoOrb;
    private Place sabotino;
    private Place duomo;
    private Place centrale;
    private Route l10;
    private Route l33;
    private Route l33b;
    private Route l4;
    private Route lM1;
    private Route lM2;

    @Before
    public void setUp(){
        t1 = new Transport("GTT", "Torino");
        t2 = new Transport("ATM", "Milano");

        polito = t1.createPlace("Politecnico");
        angoloSU = t1.createPlace("C.so Stati Uniti angolo C.so Duca");
        portaNuova = t1.createPlace("Stazione Porta Nuova");
        largoOrb = t1.createPlace("Largo Orbassano");
        Place portaSusa = t1.createPlace("Stazione Porta Susa");
        sabotino = t1.createPlace("Piazza Sabotino");
        Place viaSacchi = t1.createPlace("Via Sacchi");
        Place mauriziano = t1.createPlace("Mauriziano");
        Place xxSettembre = t1.createPlace("XX Settembre");

        duomo = t2.createPlace("Piazza del Duomo");
        centrale = t2.createPlace("Stazione Centrale");

        assertNotNull("Impossible creating places", polito);

        l10 = t1.createRoute("10", true, false);
        l33 = t1.createRoute("33", true, true);
        l33b = t1.createRoute("33/", true, true);
        l4 = t1.createRoute("4-festivo", false, true);
        lM1 = t2.createRoute("M1", true, true);
        lM2 = t2.createRoute("M2", true, false);

        assertNotNull("Impossible creating routes", l10);

        // Adding stops to routes
        l10.addStop(portaSusa);
        l10.addStop(polito);
        l10.addStop(largoOrb);

        l33.addStop(portaNuova);
        l33.addStop(angoloSU);
        l33.addStop(polito);
        l33.addStop(sabotino);

        l33b.addStop(portaNuova);
        l33b.addStop(angoloSU);
        l33b.addStop(polito);
        l33b.addStop(sabotino);

        l4.addStop(xxSettembre);
        l4.addStop(portaNuova);
        l4.addStop(viaSacchi);
        l4.addStop(mauriziano);

        lM1.addStop(duomo);
        lM1.addStop(centrale);
        
        lM2.addStop(centrale);
        lM2.addStop(duomo);
    }

    @Test
    public void testRoutesPlace(){        
        Collection<Route> routes = polito.getRoutes();

        assertNotNull("Missing routes for place " + polito.getName(), routes);
        assertEquals("Some routes are missing for " + polito.getName(), 3, routes.size());
        assertTrue("Missing route " + l10.getName(), routes.contains(l10));
        assertTrue("Missing route " + l33.getName(), routes.contains(l33));

        routes = angoloSU.getRoutes();
        assertEquals("Some routes are missing for " + angoloSU.getName(), 2, routes.size());
        assertTrue("Missing route " + l33.getName(), routes.contains(l33));

        routes = centrale.getRoutes();
        assertEquals("Some routes are missing for " + centrale.getName(), 2, routes.size());
        assertTrue("Missing route " + lM1.getName(), routes.contains(lM1));
        assertTrue("Missing route " + lM2.getName(), routes.contains(lM2));
    }
    
    @Test
    public void testTerminal(){        
        Collection<Route> routes = portaNuova.getTerminalRoutes();

        assertEquals("Some routes are missing for " + portaNuova.getName(), 2, routes.size());
        assertTrue("Missing route " + l33.getName(), routes.contains(l33));
        assertTrue("Missing route " + l4.getName(), routes.contains(l33b));

        routes = centrale.getTerminalRoutes();
        assertEquals("Some routes are missing for " + centrale.getName(), 2, routes.size());
        assertTrue("Missing route " + lM1.getName(), routes.contains(lM1));
        assertTrue("Missing route " + lM2.getName(), routes.contains(lM2));
    }

    @Test
    public void testLastTerminal(){        
        Collection<Route> routes = sabotino.getTerminalRoutes();

        assertEquals("Some routes are missing for " + sabotino.getName(), 2, routes.size());
        assertTrue("Missing route " + l33.getName(), routes.contains(l33));

        routes = duomo.getTerminalRoutes();
        assertEquals("Some routes are missing for " + duomo.getName(), 2, routes.size());
        assertTrue("Missing route " + lM1.getName(), routes.contains(lM1));
        assertTrue("Missing route " + lM2.getName(), routes.contains(lM2));
    }

    @Test
    public void testNumLines(){
        Map<String, Integer> numLinesPerStop = t1.numLinesPerStop();

        assertNotNull(numLinesPerStop);
        assertEquals("Some places are missing", 9, numLinesPerStop.size());

        long numPN = numLinesPerStop.get(portaNuova.getName());
        assertEquals("Wrong number of routes stopping in " + portaNuova.getName(), 3, numPN);

        long numPoli = numLinesPerStop.get(polito.getName());
        assertEquals("Wrong number of routes stopping in " + polito.getName(), 3, numPoli);

        long numSacchi = numLinesPerStop.get("Via Sacchi");
        assertEquals("Wrong number of routes stopping in Via Sacchi", 1, numSacchi);
    }
}
