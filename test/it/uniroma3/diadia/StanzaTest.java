package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import java.util.Set;

public class StanzaTest {
	private Stanza stanza;
	private Attrezzo lanterna;
	private Attrezzo osso;;
	private Stanza stanzaCentrale;
	private Stanza stanzaNord;
	
	@BeforeEach
	public void setUp() {
		this.stanza = new Stanza("DS2");
		this.stanzaCentrale = new Stanza("DS1");
		this.stanzaNord = new Stanza("Laboratorio");	
		
		this.lanterna = new Attrezzo("lanterna", 3);
		this.osso = new Attrezzo("osso", 1);
		
	}
	
	@Test
    public void testImpostaStanzaAdiacente_DirezioneVuota() {
        assertNull(this.stanzaCentrale.getStanzaAdiacente(Direzione.EST));
    }

    @Test
    public void testImpostaStanzaAdiacente_Nuova() {
        this.stanzaCentrale.impostaStanzaAdiacente(Direzione.NORD, stanzaNord);
        assertEquals(this.stanzaNord, this.stanzaCentrale.getStanzaAdiacente(Direzione.NORD));
    }
	
	@Test
	public void testGetNome_StanzaCentrale() {
		assertEquals("DS1", this.stanzaCentrale.getNome());
	}
	
	@Test
	public void testGetDescrizione_NonVuotaStanzaCentrale() {
		assertNotNull(this.stanzaCentrale.getDescrizione());
	}
	
	
	@Test
	public void testToString_StanzaVuota() {
		String stringaAttesa = "DS2\nUscite: \nAttrezzi nella stanza: ";
		assertEquals(stringaAttesa, this.stanza.toString());
	}
	
	@Test
	public void testToString_StanzaCentrale() {
		assertNotNull(this.stanzaCentrale.toString());
	}
	
	
	@Test
	public void testAddAttrezzo_LanternaInStanzaCentrale() {
		assertNotNull(this.stanzaCentrale.addAttrezzo(lanterna));
	}
	
	@Test
	public void testHasAttrezzo_OssoInStanza() {
		this.stanza.addAttrezzo(osso);
		assertTrue(this.stanza.hasAttrezzo("osso"));
	}
	
	@Test
	public void testHasAttrezzo_OssoInStanzaSenzaAttrezzi() {
		assertFalse(this.stanza.hasAttrezzo("osso"));
	}
	
	@Test
	public void testGetAttrezzo_AssenteInStanza() {
		assertNull(this.stanza.getAttrezzo("osso"));
	}
	
	@Test
	public void testGetAttrezzo_LanternaInStanza() {
		this.stanza.addAttrezzo(lanterna);
		assertEquals(this.lanterna, this.stanza.getAttrezzo("lanterna"));
	}
	
	@Test
	public void testGetDirezioni_StanzaIsolata() {
		assertEquals(0, this.stanza.getDirezioni().size());
	}
	
	@Test
    public void testGetDirezioni_Stanza() {
        this.stanza.impostaStanzaAdiacente(Direzione.EST, stanzaCentrale);
        
        Set<Direzione> direzioni = this.stanza.getDirezioni();
        
        assertEquals(1, direzioni.size());
        
        assertTrue(direzioni.contains(Direzione.EST));
    }
	
	@Test 
	public void testRemoveAttrezzo_LanternaDaStanza() {
		this.stanza.addAttrezzo(lanterna);
		assertTrue(this.stanza.removeAttrezzo(lanterna));
	}
	
	@Test 
	public void testRemoveAttrezzo_NonPresenteDaStanza() {
		assertFalse(this.stanza.removeAttrezzo(lanterna));
	}

}
