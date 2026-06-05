package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.Set;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {
	private Attrezzo osso;
	private Attrezzo lanterna;
	private Borsa borsa;
	
	@BeforeEach
	public void setUp() {
		this.borsa = new Borsa();
		this.osso = new Attrezzo("osso", 1);
		this.lanterna = new Attrezzo("lanterna", 3);
	}

	@Test
	public void testAddAttrezzo_Osso() {
		assertTrue(this.borsa.addAttrezzo(osso));
	}
	
	@Test
	public void testGetAttrezzo_Osso() {
		this.borsa.addAttrezzo(osso);
		assertEquals(osso, this.borsa.getAttrezzo("osso"));
	}
	
	@Test
	public void testGetAttrezzo_NonEsistente() {
		assertNull(this.borsa.getAttrezzo("osso"));
	}
	
	@Test
	public void testGetPeso_InizioPartita() {
		assertEquals(0, this.borsa.getPeso());
	}
	
	@Test
	public void testGetPeso_ConAttrezzi() {
		this.borsa.addAttrezzo(osso);
		this.borsa.addAttrezzo(lanterna);
		assertEquals(4, this.borsa.getPeso());
	}
	
	@Test
	public void testIsEmpty_SenzaAttrezzi() {
		assertTrue(this.borsa.isEmpty());
	}
	
	@Test
	public void testIsEmpty_ConAttrezzi() {
		this.borsa.addAttrezzo(osso);
		assertFalse(this.borsa.isEmpty());
	}
	
	@Test
	public void testHasAttrezzo_Osso() {
		this.borsa.addAttrezzo(osso);
		assertTrue(this.borsa.hasAttrezzo("osso"));
	}
	
	@Test
	public void testHasAttrezzo_NonEsistente() {
		assertFalse(this.borsa.hasAttrezzo("osso"));
	}
	
	@Test
	public void testRemoveAttrezzo_Osso() {
		this.borsa.addAttrezzo(osso);
		assertEquals(osso, this.borsa.removeAttrezzo("osso"));
	}
	
	@Test
	public void testRemoveAttrezzo_NonEsistente() {
		assertNull(this.borsa.removeAttrezzo("osso"));
	}
	
	@Test
	public void testToString_BorsaVuota() {
		String stringaAttesa = "Borsa vuota";
		assertEquals(stringaAttesa, this.borsa.toString());
	}
	
	@Test
	public void testToString_BorsaConOsso() {
		this.borsa.addAttrezzo(osso);
		String stringaAttesa = "Contenuto borsa (1kg/10kg): osso (1kg) ";
		assertEquals(stringaAttesa, this.borsa.toString());
	}
	
	@Test
	public void testAddAttrezzo_PesoTroppoElevato() {
	    Attrezzo incudine = new Attrezzo("incudine", 11);
	    assertFalse(this.borsa.addAttrezzo(incudine));
	}

	@Test
	public void testAddAttrezzo_TroppiAttrezzi() {
	    for (int i = 0; i < 10; i++) {
	        Attrezzo piuma = new Attrezzo("piuma" + i, 1);
	        this.borsa.addAttrezzo(piuma);
	    }
	    Attrezzo undicesimo = new Attrezzo("piumaExtra", 1);
	    assertFalse(this.borsa.addAttrezzo(undicesimo));
	}
	@Test
    public void testGetContenutoOrdinatoPerPeso() {
        Borsa borsa = new Borsa(30);
        Attrezzo piombo = new Attrezzo("piombo", 10);
        Attrezzo ps = new Attrezzo("ps", 5);
        Attrezzo piuma = new Attrezzo("piuma", 1);
        Attrezzo libro = new Attrezzo("libro", 5);
        
        borsa.addAttrezzo(piombo);
        borsa.addAttrezzo(ps);
        borsa.addAttrezzo(piuma);
        borsa.addAttrezzo(libro);
        
        List<Attrezzo> ordinata = borsa.getContenutoOrdinatoPerPeso();
        assertEquals("piuma", ordinata.get(0).getNome());
        assertEquals("libro", ordinata.get(1).getNome());
        assertEquals("ps", ordinata.get(2).getNome());
        assertEquals("piombo", ordinata.get(3).getNome());
    }

    @Test
    public void testGetContenutoOrdinatoPerNome() {
        Borsa borsa = new Borsa(30);
        Attrezzo piombo = new Attrezzo("piombo", 10);
        Attrezzo ps = new Attrezzo("ps", 5);
        Attrezzo piuma = new Attrezzo("piuma", 1);
        Attrezzo libro = new Attrezzo("libro", 5);
        
        borsa.addAttrezzo(piombo);
        borsa.addAttrezzo(ps);
        borsa.addAttrezzo(piuma);
        borsa.addAttrezzo(libro);
        
        Attrezzo[] ordinata = borsa.getContenutoOrdinatoPerNome().toArray(new Attrezzo[0]);
        
        assertEquals("libro", ordinata[0].getNome());
        assertEquals("piombo", ordinata[1].getNome());
        assertEquals("piuma", ordinata[2].getNome());
        assertEquals("ps", ordinata[3].getNome());
    }

    @Test
    public void testGetContenutoRaggruppatoPerPeso() {
        Borsa borsa = new Borsa(30);
        Attrezzo piombo = new Attrezzo("piombo", 10);
        Attrezzo ps = new Attrezzo("ps", 5);
        Attrezzo piuma = new Attrezzo("piuma", 1);
        Attrezzo libro = new Attrezzo("libro", 5);
        
        borsa.addAttrezzo(piombo);
        borsa.addAttrezzo(ps);
        borsa.addAttrezzo(piuma);
        borsa.addAttrezzo(libro);
        
        Map<Integer, Set<Attrezzo>> raggruppati = borsa.getContenutoRaggruppatoPerPeso();
        
        assertEquals(3, raggruppati.keySet().size());
        
        assertTrue(raggruppati.get(1).contains(piuma));
        
        assertEquals(2, raggruppati.get(5).size());
        assertTrue(raggruppati.get(5).contains(libro));
        assertTrue(raggruppati.get(5).contains(ps));
        
        assertTrue(raggruppati.get(10).contains(piombo));
    }
}
