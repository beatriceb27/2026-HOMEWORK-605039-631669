package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtectedTest {

    private StanzaMagicaProtected stanzaMagica;
    private Attrezzo osso;
    private Attrezzo lanterna;

    @BeforeEach
    public void setUp() {
       
        this.stanzaMagica = new StanzaMagicaProtected("Stanza Incantata", 1);
        
        this.osso = new Attrezzo("osso", 2);
        this.lanterna = new Attrezzo("lanterna", 3);
    }

    @Test
    public void testAddAttrezzo_NormaleSottoSoglia() {
        this.stanzaMagica.addAttrezzo(this.osso);
        
        assertTrue(this.stanzaMagica.hasAttrezzo("osso"));
        assertEquals(2, this.stanzaMagica.getAttrezzo("osso").getPeso());
    }

    @Test
    public void testAddAttrezzo_MagicoSopraSoglia() {
        this.stanzaMagica.addAttrezzo(this.osso);
        
        this.stanzaMagica.addAttrezzo(this.lanterna);
        
        assertFalse(this.stanzaMagica.hasAttrezzo("lanterna"));
        
        assertTrue(this.stanzaMagica.hasAttrezzo("anretnal"));
        
        assertEquals(6, this.stanzaMagica.getAttrezzo("anretnal").getPeso());
    }
}