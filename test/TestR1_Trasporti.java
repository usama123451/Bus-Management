package it.polito.oop.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

import it.polito.oop.bus.Transport;

public class TestR1_Trasporti  {

	private static final String MUNICIPALITY = "Torino";
	private static final String COMPANY = "GTT";
	private Transport t;

	@Before
	public void setUp(){
		t = new Transport(COMPANY,MUNICIPALITY);
	}

	@Test
	public void testTransport(){
		assertEquals("Wrong company name",
				COMPANY,t.getCompany());
		assertEquals("Wrong municipality",
				MUNICIPALITY,t.getMunicipality());
	}
	
	@Test
	public void testPrice(){
		double price = 1.2;
		t.setPrice(price);
		
		assertEquals("Wrong price",
					price, t.getPrice(), 0.001);
	}

	@Test
	public void testWrongPrice(){
		double price = -1;
		assertThrows("-1 is not a valid price", 
					IllegalArgumentException.class, 
					()->t.setPrice(price));

	}

	@Test
	public void testWrongPrice2(){
		double price = 0;
		assertThrows("Zero is not a valid price", 
					IllegalArgumentException.class, 
					()->t.setPrice(price));

	}
}
