package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {

    private StanzaBloccata stanza;
    private Stanza stanzaAdiacenteNord;
    private Stanza stanzaAdiacenteSud;
    private Attrezzo chiave;

    @BeforeEach
    public void setUp() {
        this.stanza = new StanzaBloccata("Prigione", Direzione.NORD, "chiave");
        this.stanzaAdiacenteNord = new Stanza("Libertà");
        this.stanzaAdiacenteSud = new Stanza("Bagno");

        this.stanza.impostaStanzaAdiacente(Direzione.NORD, this.stanzaAdiacenteNord);
        this.stanza.impostaStanzaAdiacente(Direzione.SUD, this.stanzaAdiacenteSud);
        this.chiave = new Attrezzo("chiave", 1);
    }

    @Test
    public void testGetStanzaAdiacente_DirezioneBloccata_SenzaChiave() {
        Stanza stanzaRestituita = this.stanza.getStanzaAdiacente(Direzione.NORD);
        assertEquals(this.stanza, stanzaRestituita);
    }

    @Test
    public void testGetStanzaAdiacente_DirezioneBloccata_ConChiave() {
        this.stanza.addAttrezzo(this.chiave);
        Stanza stanzaRestituita = this.stanza.getStanzaAdiacente(Direzione.NORD);
        assertEquals(this.stanzaAdiacenteNord, stanzaRestituita);
    }

    @Test
    public void testGetStanzaAdiacente_DirezioneLibera() {
        Stanza stanzaRestituita = this.stanza.getStanzaAdiacente(Direzione.SUD);
        assertEquals(this.stanzaAdiacenteSud, stanzaRestituita);
    }
}