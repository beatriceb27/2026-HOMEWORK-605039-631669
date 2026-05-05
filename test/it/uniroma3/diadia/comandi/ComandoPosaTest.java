package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.IOConsole;

public class ComandoPosaTest {

    private Partita partita;
    private ComandoPosa comando;
    private Attrezzo attrezzo;

    @BeforeEach
    public void setUp() {
        this.partita = new Partita();
        this.comando = new ComandoPosa();
        this.comando.setIo(new IOConsole());
        this.attrezzo = new Attrezzo("osso", 2);
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