package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.IOConsole;

public class ComandoGuardaTest {

    private Partita partita;
    private ComandoGuarda comando;

    @BeforeEach
    public void setUp() {
    	this.partita = new Partita(Labirinto.newBuilder().getLabirinto());
        this.comando = new ComandoGuarda();
        this.comando.setIo(new IOConsole(new java.util.Scanner("")));
        }

    @Test
    public void testEsegui_NonModificaStatoPartita() {
        int cfuIniziali = this.partita.getCfu();
        String stanzaIniziale = this.partita.getStanzaCorrente().getNome();
        
        this.comando.esegui(this.partita);
        assertEquals(cfuIniziali, this.partita.getCfu());
        assertEquals(stanzaIniziale, this.partita.getStanzaCorrente().getNome());
    }
}