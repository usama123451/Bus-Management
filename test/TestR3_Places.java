package it.polito.oop.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import it.polito.oop.bus.Transport;
import it.polito.oop.bus.Place;


public class TestR3_Places {

	private static final String POLITECNICO = "Politecnico";
	private static final String MUNICIPALITY = "Torino";
	private static final String COMPANY = "GTT";
	private static final String ANGOLO_SU_DUCA = "C.so Stati Uniti angolo C.so Duca";
	private Transport t;
	private Place lg1;
	private Place lg2;
	private Place lg4;

	@Before
	public void setUp(){
		t = new Transport(COMPANY,MUNICIPALITY);
		lg1 = t.createPlace(POLITECNICO);	
		lg2 = t.createPlace(ANGOLO_SU_DUCA);
		t.createPlace("Stazione Porta Nuova");
		lg4 = t.createPlace("Largo Orbassano");

	}

	@Test
	public void testCreatePlace(){
		assertNotNull("Could not create place "+POLITECNICO, lg1);

		assertEquals("Wrong place name",
					POLITECNICO, lg1.getName());

	}
	
	@Test
	public void testGetPlaces(){
		
		Collection<Place> places = t.getPlaces();
		
		assertNotNull("Missing list of places", places);
		assertEquals("Some places are missing",
					4,places.size());
		assertTrue("Missing place from the list " + lg2.getName(),
					places.contains(lg2));
		assertTrue("Missing place from the list " + lg4.getName(),
					places.contains(lg4));
	}
	
	@Test
	public void testGetLuogo(){		
		Place lg = t.getPlaces(ANGOLO_SU_DUCA);
		
		assertNotNull("Missing place", lg);
		assertSame("Different place expected", lg2,lg);
	}
}
