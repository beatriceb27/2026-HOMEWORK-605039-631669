package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FabbricaDiComandiRiflessivaTest {

    private FabbricaDiComandiRiflessiva factory;

    @BeforeEach
    public void setUp() {
        this.factory = new FabbricaDiComandiRiflessiva();
    }

    @Test
    public void testCostruisciComando_ComandoValido() {
        Comando comando = this.factory.costruisciComando("vai nord");
        
        assertEquals("ComandoVai", comando.getClass().getSimpleName());
        assertEquals("nord", comando.getParametro());
    }

    @Test
    public void testCostruisciComando_ComandoValidoSenzaParametro() {
        Comando comando = this.factory.costruisciComando("fine");
        assertEquals("ComandoFine", comando.getClass().getSimpleName());
        assertNull(comando.getParametro());
    }

    @Test
    public void testCostruisciComando_ComandoInesistente() {
        Comando comando = this.factory.costruisciComando("teletrasporto");
        assertEquals("ComandoNonValido", comando.getClass().getSimpleName());
    }
}