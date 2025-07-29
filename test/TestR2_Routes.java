package it.polito.oop.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import it.polito.oop.bus.Transport;
import it.polito.oop.bus.Route;

public class TestR2_Routes {
	private static final String MUNICIPALITY = "Torino";
	private static final String COMPANY = "GTT";
	private Transport t;

	@Before
	public void setUp(){
		t = new Transport(COMPANY,MUNICIPALITY);
	}

	@Test
	public void testCreateRoute(){
		boolean weekday = true;
		boolean holiday = false;
		Route l = t.createRoute("1",weekday,holiday);
		
		assertNotNull("Missing route", l);
		assertEquals("Wrong route name",
					"1",l.getName());
		assertEquals("Wrong weekday flag",
					weekday,l.isWeekday());
		assertEquals("Wrong holiday flag",
					holiday,l.isHoliday());
	}
	
	@Test
	public void testWrongRoute(){
		boolean weekday = false;
		boolean holiday = false;
		
		assertThrows("A route must operate on weekday or holiday",
		IllegalArgumentException.class,
		 ()->t.createRoute("1",weekday,holiday));
	}
	
	@Test
	public void testGetRoutes(){
		Route l1 = t.createRoute("1",true,false);
		Route l2 = t.createRoute("33",true,true);
		Route l3 = t.createRoute("58f",false,true);
		
		Collection<Route> routes = t.getRoutes();
		
		assertNotNull("Missing routes", routes);
		assertEquals("Some routes are missing", 3, routes.size());	
		assertTrue("Could not find route "+l1.getName(), routes.contains(l1));
		assertTrue("Could not find route "+l2.getName(), routes.contains(l2));
		assertTrue("Could not find route "+l3.getName(), routes.contains(l3));
	}
	
	@Test
	public void testGetRoute(){
		t.createRoute("1",true,false);
		Route l33 = t.createRoute("33",true,true);
		t.createRoute("58f",false,true);
		
		Route route = t.getRoute("33");
		
		assertNotNull("Could not retrieve route 33", route);
		assertSame("Wrong route returned", l33, route);
	}
	
	
}
