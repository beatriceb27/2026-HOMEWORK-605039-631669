package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
    private Stanza stanzaIniziale;
    private Stanza stanzaCorrente;
    private Stanza stanzaVincente;

    private Labirinto() {}

    public void setStanzaIniziale(Stanza stanzaIniziale) { this.stanzaIniziale = stanzaIniziale; }
    public void setStanzaVincente(Stanza stanzaVincente) { this.stanzaVincente = stanzaVincente; }
    public Stanza getStanzaVincente() { return stanzaVincente; }
    public void setStanzaCorrente(Stanza stanzaCorrente) { this.stanzaCorrente = stanzaCorrente; }
    public Stanza getStanzaCorrente() { return this.stanzaCorrente; }
    public Stanza getStanzaIniziale() { return this.stanzaIniziale; }

    public static LabirintoBuilder newBuilder() {
        return new LabirintoBuilder();
    }

    public static class LabirintoBuilder {
        private Labirinto labirinto;
        private Map<String, Stanza> mappaStanze;
        private Stanza ultimaStanzaAggiunta;

        public LabirintoBuilder() {
            this.labirinto = new Labirinto();
            this.mappaStanze = new HashMap<>();
        }
       
        public Map<String, Stanza> getListaStanze() {
            return this.mappaStanze;
        }
        public Labirinto getLabirinto() { return this.labirinto; }

        public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
            Stanza iniziale = new Stanza(nomeStanza);
            this.labirinto.setStanzaIniziale(iniziale);
            this.aggiornaMappaEUltimaStanza(iniziale);
            return this;
        }

        public LabirintoBuilder addStanzaVincente(String nomeStanza) {
            Stanza vincente = new Stanza(nomeStanza);
            this.labirinto.setStanzaVincente(vincente);
            this.aggiornaMappaEUltimaStanza(vincente);
            return this;
        }

        public LabirintoBuilder addStanza(String nomeStanza) {
            Stanza stanza = new Stanza(nomeStanza);
            this.aggiornaMappaEUltimaStanza(stanza);
            return this;
        }

        public LabirintoBuilder addStanzaBloccata(String nomeStanza, String direzioneBloccata, String passepartout) {
            Stanza stanza = new StanzaBloccata(nomeStanza, Direzione.valueOf(direzioneBloccata.toUpperCase()), passepartout);
            this.aggiornaMappaEUltimaStanza(stanza);
            return this;
        }

        public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
            if (this.ultimaStanzaAggiunta != null) {
                Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
                this.ultimaStanzaAggiunta.addAttrezzo(attrezzo);
            }
            return this;
        }

        public LabirintoBuilder addAdiacenza(String stanzaCorrente, String stanzaAdiacente, String direzione) {
            Stanza corrente = this.mappaStanze.get(stanzaCorrente);
            Stanza adiacente = this.mappaStanze.get(stanzaAdiacente);
            if (corrente != null && adiacente != null) {
                corrente.impostaStanzaAdiacente(Direzione.valueOf(direzione.toUpperCase()), adiacente);
            }
            return this;
        }

        private void aggiornaMappaEUltimaStanza(Stanza stanza) {
            this.ultimaStanzaAggiunta = stanza;
            this.mappaStanze.put(stanza.getNome(), stanza);
        }
    }
}