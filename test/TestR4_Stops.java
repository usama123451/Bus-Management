package it.polito.oop.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import it.polito.oop.bus.Transport;
import it.polito.oop.bus.Route;
import it.polito.oop.bus.Place;


public class TestR4_Stops {
	private Transport t;
	private Place poli;
	private Place angoloSU;
	private Place pn;
	private Route l1;
	private Route l33;

	@Before
	public void setUp(){
		t = new Transport("GTT","Torino");
		
		poli = t.createPlace("Politecnico");
		angoloSU = t.createPlace("C.so Stati Uniti angolo C.so Duca");
		pn = t.createPlace("Stazione Porta Nuova");
		l1 = t.createRoute("1",true,false);
		l33 = t.createRoute("33",true,true);

		assertNotNull("Could not create places", poli);
		assertNotNull("Could not create routes", l1);
	}

	@Test
	public void testAddStop(){
		
		l33.addStop(pn);
		l33.addStop(angoloSU);
		l33.addStop(poli);
		
		Collection<Place> stops = l33.getStops();
		
		assertNotNull("No stops for line " + l33.getName(), stops);
		assertEquals("Some stops are missing", 
					3, stops.size());
		assertTrue("Stops do not include"+angoloSU.getName(), stops.contains(angoloSU));
	}

	@Test
	public void testFirstTerminal(){
		l1.addStop(pn);
		l1.addStop(angoloSU);
		l1.addStop(poli);

		assertSame("Wrong first terminal", 
				   pn, l1.getFirstTerminal());
	}

	@Test
	public void testLastTerminal(){
		l1.addStop(pn);
		l1.addStop(angoloSU);
		l1.addStop(poli);

		assertSame("Wrong last terminal", 
		           poli, l1.getLastTerminal());
	}
}
