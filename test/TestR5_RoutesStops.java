package it.polito.oop.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import it.polito.oop.bus.Transport;
import it.polito.oop.bus.Route;
import it.polito.oop.bus.Place;

public class TestR5_RoutesStops {
	private Transport t;
	private Place polito;
	private Place angoloSU;
	private Place portaNuova;
	private Place lg4;
	private Place sabotino;
	private Route l10;
	private Route l33;
	private Route l4;

	@Before
	public void setUp(){
		t = new Transport("GTT","Torino");
		
		polito = t.createPlace("Politecnico");
		angoloSU = t.createPlace("C.so Stati Uniti angolo C.so Duca");
		portaNuova = t.createPlace("Stazione Porta Nuova");
		lg4 = t.createPlace("Largo Orbassano");
		Place lg5 = t.createPlace("Stazione Porta Susa");
		sabotino = t.createPlace("Piazza Sabotino");
		Place lg7 = t.createPlace("Via Sacchi");
		Place lg8 = t.createPlace("Mauriziano");
		Place lg9 = t.createPlace("XX Settembre");
		
		assertNotNull("Impossible creating places", polito);

		l10 = t.createRoute("10",true,false);
		l33 = t.createRoute("33",true,true);
		Route l33b = t.createRoute("33/",true,true);
		l4 = t.createRoute("4-festivo",false,true);
		
		assertNotNull("Impossible creating routes", l10);

		l10.addStop(lg5);
		l10.addStop(polito);
		l10.addStop(lg4);
		
		l33.addStop(portaNuova);
		l33.addStop(angoloSU);
		l33.addStop(polito);
		l33.addStop(sabotino);

		l33b.addStop(portaNuova);
		l33b.addStop(angoloSU);
		l33b.addStop(polito);
		l33b.addStop(sabotino);

		l4.addStop(lg9);
		l4.addStop(portaNuova);
		l4.addStop(lg7);
		l4.addStop(lg8);
	}

	@Test
	public void testRoutesPlace(){		
		Collection<Route> routes = polito.getRoutes();

		assertNotNull("Missing routes for place "+polito.getName(), 
				      routes);
		assertEquals("Some routes are missing for " + polito.getName(),
					 3, routes.size());
		assertTrue("Missing route "+l10.getName(), routes.contains(l10));
		assertTrue("Missing route "+l33.getName(), routes.contains(l33));

		routes = angoloSU.getRoutes();

		assertEquals("Some routes are missing for " + angoloSU.getName(),
					 2, routes.size());
		assertTrue("Missing route "+l33.getName(), routes.contains(l33));
	}
	
	@Test
	public void testTerminal(){		
		Collection<Route> routes = portaNuova.getTerminalRoutes();

		assertEquals("Some routes are missing for " + portaNuova.getName(),
					 2,routes.size());
		assertTrue("Missing route "+l33.getName(), routes.contains(l33));
	}

	@Test
	public void testLastTerminal(){		
		Collection<Route> routes = sabotino.getTerminalRoutes();

		assertEquals("Some routes are missing for " + sabotino.getName(),
					 2, routes.size());
		assertTrue("Missing route "+l33.getName(), routes.contains(l33));
	}

	@Test
	public void testNumLines(){
		Map<String,Integer> numLinesPerStop = t.numLinesPerStop();

		assertNotNull(numLinesPerStop);

		assertEquals("Some places are missing", 9, numLinesPerStop.size());

		long numPN = numLinesPerStop.get(portaNuova.getName());
		assertEquals("Wrong number of routes stopping in " + portaNuova.getName(),
					 3, numPN);

		long numPoli = numLinesPerStop.get(polito.getName());
		assertEquals("Wrong number of routes stopping in " + polito.getName(),
					 3, numPoli);
					}
}
