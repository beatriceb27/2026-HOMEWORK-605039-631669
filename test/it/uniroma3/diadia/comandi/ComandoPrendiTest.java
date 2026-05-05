package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.IOConsole;

public class ComandoPrendiTest {

    private Partita partita;
    private ComandoPrendi comando;
    private Attrezzo attrezzo;

    @BeforeEach
    public void setUp() {
        this.partita = new Partita();
        this.comando = new ComandoPrendi();
        this.comando.setIo(new IOConsole());
        this.attrezzo = new Attrezzo("lanterna", 3);
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