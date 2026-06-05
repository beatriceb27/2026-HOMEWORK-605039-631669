package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
public class ComandoVaiTest {

	private Partita partita;
	private ComandoVai comando;
	@BeforeEach
    public void setUp() {
		Labirinto bilocale = Labirinto.newBuilder()
                .addStanzaIniziale("Atrio")
                .addStanza("Biblioteca")
                .addAdiacenza("Atrio", "Biblioteca", "nord")
                .getLabirinto();
                
        this.partita = new Partita(bilocale);
        this.comando = new ComandoVai();
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