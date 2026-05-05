package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.IOConsole; // O qualsiasi IO fittizio tu stia usando

public class ComandoVaiTest {

	private Partita partita;
	private Stanza stanzaPartenza;
	private Stanza stanzaArrivo;
	private ComandoVai comando;

	@BeforeEach
	public void setUp() {
		this.partita = new Partita();
		this.stanzaPartenza = new Stanza("Partenza");
		this.stanzaArrivo = new Stanza("Arrivo");
		
		this.stanzaPartenza.impostaStanzaAdiacente("nord", stanzaArrivo);
		this.partita.setStanzaCorrente(stanzaPartenza);
		
		this.comando = new ComandoVai();
		this.comando.setIo(new IOConsole()); 
	}

	@Test
	public void testEsegui_DirezioneValida() {
		this.comando.setParametro("nord");
		this.comando.esegui(partita);
		
		assertEquals("Arrivo", this.partita.getStanzaCorrente().getNome());
		assertEquals(19, this.partita.getCfu());
	}

	@Test
	public void testEsegui_DirezioneInesistente() {
		this.comando.setParametro("sud"); 
		this.comando.esegui(partita);
		
		assertEquals("Partenza", this.partita.getStanzaCorrente().getNome());
		assertEquals(20, this.partita.getCfu());
	}
	
	@Test
	public void testEsegui_DirezioneNulla() {
		this.comando.setParametro(null); 
		this.comando.esegui(partita);
		
		assertEquals("Partenza", this.partita.getStanzaCorrente().getNome());
	}
}