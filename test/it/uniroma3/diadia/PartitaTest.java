package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.Labirinto;
public class PartitaTest {
    private Partita partita;
    private Stanza stanza;
  

    @BeforeEach
    public void setUp() {
    	this.partita = new Partita(Labirinto.newBuilder().getLabirinto());		
		this.stanza = new Stanza("Biblioteca");
    }
    
    @Test
    public void testGetStanzaCorrente_Stanza() {
    	this.partita.setStanzaCorrente(stanza);
    	assertEquals("Biblioteca", this.partita.getStanzaCorrente().getNome());
    }

    @Test
    public void testIsFinita_InizioPartita() {
        assertFalse(this.partita.isFinita());
    }

    @Test
    public void testIsFinita_SenzaCfu() {
        this.partita.setCfu(0);
        assertTrue(this.partita.isFinita());
    }
    
    @Test
    public void testIsFinita_AncoraCfu() {
        this.partita.setCfu(5);
        assertFalse(this.partita.isFinita());
    }

    @Test
    public void testSetFinita_Forzata() {
        this.partita.setFinita();
        assertTrue(this.partita.isFinita());
    }
    
    @Test
    public void testGetCfu() {
    	this.partita.setCfu(5);
    	assertEquals(5, this.partita.getCfu());
    }

    @Test
    public void testVinta_InizioPartita() {
        assertFalse(this.partita.vinta());
    }
        
    @Test
    public void testVinta_StanzaVincente() {
    	this.partita.setStanzaCorrente(stanza);
    	assertTrue(this.partita.vinta());
    }
    
}
