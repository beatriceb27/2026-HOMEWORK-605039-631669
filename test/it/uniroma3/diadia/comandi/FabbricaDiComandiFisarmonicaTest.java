package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
@Deprecated
public class FabbricaDiComandiFisarmonicaTest {

    private FabbricaDiComandiFisarmonica fabbrica;

    @BeforeEach
    public void setUp() {
        this.fabbrica = new FabbricaDiComandiFisarmonica();
    }

    @Test
    public void testCostruisciComando_VaiConParametro() {
        Comando comando = this.fabbrica.costruisciComando("vai nord");
        
        assertEquals("vai", comando.getNome());
        assertEquals("nord", comando.getParametro());
    }

    @Test
    public void testCostruisciComando_PrendiConParametro() {
        Comando comando = this.fabbrica.costruisciComando("prendi osso");
        
        assertEquals("prendi", comando.getNome());
        assertEquals("osso", comando.getParametro());
    }

    @Test
    public void testCostruisciComando_SenzaParametro() {
        Comando comando = this.fabbrica.costruisciComando("fine");
        
        assertEquals("fine", comando.getNome());
        assertNull(comando.getParametro());
    }

    @Test
    public void testCostruisciComando_ComandoNonValido() {
        Comando comando = this.fabbrica.costruisciComando("parolainventata");
        
        assertEquals("comando non valido", comando.getNome());
    }
}