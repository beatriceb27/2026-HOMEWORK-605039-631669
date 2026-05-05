package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole;

public class ComandoNonValidoTest {

    private Partita partita;
    private ComandoNonValido comando;

    @BeforeEach
    public void setUp() {
        this.partita = new Partita();
        this.comando = new ComandoNonValido();
        this.comando.setIo(new IOConsole());
    }

    @Test
    public void testEsegui_NonModificaStatoPartita() {
        int cfuIniziali = this.partita.getCfu();
        
        this.comando.esegui(this.partita);
        
        assertEquals(cfuIniziali, this.partita.getCfu());
    }
}