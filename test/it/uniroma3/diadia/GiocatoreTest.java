package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.giocatore.Giocatore;

class GiocatoreTest {
	private Giocatore giocatore;
	
	@BeforeEach
	public void setUp() {
		this.giocatore = new Giocatore();	
	}

	@Test
	public void testGetCfu_InizioPartita() {
		assertEquals(20, this.giocatore.getCfu());
	}
	
	@Test
	public void testSetCfu_Modificati() {
		this.giocatore.setCfu(5);
		assertEquals(5, this.giocatore.getCfu());
	}
	
	@Test
	public void testGetBorsa_NonVuota() {
		assertNotNull(this.giocatore.getBorsa());
	}

}
