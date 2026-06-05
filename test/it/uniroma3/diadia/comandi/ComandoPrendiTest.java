package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendiTest {

    private Partita partita;
    private ComandoPrendi comando;
    private Attrezzo attrezzo;

    @BeforeEach
    public void setUp() {
    	Labirinto monolocaleConAttrezzo = Labirinto.newBuilder()
                .addStanzaIniziale("Atrio")
                .addAttrezzo("lanterna", 3) 
                .getLabirinto();
                
        this.partita = new Partita(monolocaleConAttrezzo);
        this.comando = new ComandoPrendi();
    }

    @Test
    public void testEsegui_AttrezzoPresente() {
        this.partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
        
        this.comando.setParametro("lanterna");
        this.comando.esegui(this.partita);
        
        assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("lanterna"));
        assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("lanterna"));
    }

    @Test
    public void testEsegui_AttrezzoAssente() {
        this.comando.setParametro("spada");
        this.comando.esegui(this.partita);
        
        assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
    }
}