package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {

    private StanzaBuia stanza;
    private Attrezzo lanterna;

    @BeforeEach
    public void setUp() {
        this.stanza = new StanzaBuia("Cantina", "lanterna");
        this.lanterna = new Attrezzo("lanterna", 3);
    }

    @Test
    public void testGetDescrizione_SenzaLanterna_BuioPesto() {
        String descrizione = this.stanza.getDescrizione();
        
        assertEquals("qui c'è un buio pesto", descrizione);
    }

    @Test
    public void testGetDescrizione_ConLanterna_Luce() {
        this.stanza.addAttrezzo(this.lanterna);
        
        String descrizione = this.stanza.getDescrizione();
        
        
        assertNotEquals("qui c'è un buio pesto", descrizione);
        
        assertTrue(descrizione.contains("Cantina"));
    }
}