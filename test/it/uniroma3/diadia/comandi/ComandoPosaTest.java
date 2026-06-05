package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosaTest {

    private Partita partita;
    private ComandoPosa comando;
    private Attrezzo attrezzo;

    @BeforeEach
    public void setUp() {
    	Labirinto monolocale = Labirinto.newBuilder()
                .addStanzaIniziale("Atrio")
                .addStanzaVincente("Atrio") 
                .getLabirinto();
                
        this.partita = new Partita(monolocale);
        this.comando = new ComandoPosa();
        
        Attrezzo osso = new Attrezzo("osso", 2);
        this.partita.getGiocatore().getBorsa().addAttrezzo(osso);
    }

    @Test
    public void testEsegui_AttrezzoInBorsa() {
        this.partita.getGiocatore().getBorsa().addAttrezzo(this.attrezzo);
        this.comando.setParametro("osso");
        this.comando.esegui(this.partita);
        
        assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
        assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("osso"));
    }

    @Test
    public void testEsegui_AttrezzoNonPosseduto() {
        this.comando.setParametro("spada");
        this.comando.esegui(this.partita);
        assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
    }
}