package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.IOConsole;

public class ComandoFineTest {

    private Partita partita;
    private ComandoFine comando;

    @BeforeEach
    public void setUp() {
    	this.partita = new Partita(Labirinto.newBuilder().getLabirinto());
        this.partita.setCfu(20); 
        
        this.comando = new ComandoFine();
        this.comando.setIo(new IOConsole(new java.util.Scanner("")));
        }

    @Test
    public void testEsegui_TerminaPartita() {
        assertFalse(this.partita.isFinita());
        
        this.comando.esegui(this.partita);
        
        assertTrue(this.partita.isFinita());
    }
}