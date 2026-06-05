package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class LabirintoTest {
	private Labirinto labirinto;
	private Stanza biblioteca;
	private Stanza stanza;
	private Stanza atrio;
	
	@BeforeEach
	public void setUp() {
		this.labirinto = Labirinto.newBuilder().getLabirinto();
		this.biblioteca = new Stanza("Biblioteca");
		this.stanza = new Stanza("DS2");
		this.atrio = new Stanza("Atrio");
	}
	
	@Test
	public void testGetStanzaVincente_Biblioteca() {
		assertEquals(this.biblioteca.getNome(), this.labirinto.getStanzaVincente().getNome());
	}
	
	@Test
	public void testGetStanzaCorrente_Stanza() {
		this.labirinto.setStanzaCorrente(stanza);
		assertEquals(stanza, this.labirinto.getStanzaCorrente());
	}
	
	@Test
	public void testGetStanzaCorrente_InizioPartita() {
		assertEquals(this.atrio.getNome(), this.labirinto.getStanzaCorrente().getNome());
	}

}
