package it.uniroma3.diadia.ambienti;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaProtected {

    protected String nome;
    protected Map<String, Attrezzo> attrezzi;
    protected Map<String, StanzaProtected> stanzeAdiacenti;

    public StanzaProtected(String nome) {
        this.nome = nome;
        this.attrezzi = new HashMap<>();
        this.stanzeAdiacenti = new HashMap<>();
    }

    public void impostaStanzaAdiacente(String direzione, StanzaProtected stanza) {
        this.stanzeAdiacenti.put(direzione, stanza);
    }

    public StanzaProtected getStanzaAdiacente(String direzione) {
        return this.stanzeAdiacenti.get(direzione);
    }

    public Set<String> getDirezioni() {
        return this.stanzeAdiacenti.keySet();
    }

    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (attrezzo != null) {
            this.attrezzi.put(attrezzo.getNome(), attrezzo);
            return true;
        }
        return false;
    }

    public boolean hasAttrezzo(String nomeAttrezzo) {
        return this.attrezzi.containsKey(nomeAttrezzo);
    }

    public Attrezzo getAttrezzo(String nomeAttrezzo) {
        return this.attrezzi.get(nomeAttrezzo);
    }

    public boolean removeAttrezzo(Attrezzo attrezzo) {
        if (attrezzo != null && this.attrezzi.containsKey(attrezzo.getNome())) {
            this.attrezzi.remove(attrezzo.getNome());
            return true;
        }
        return false;
    }

    public Collection<Attrezzo> getAttrezzi() {
        return this.attrezzi.values();
    }

    public String getDescrizione() {
        return this.toString();
    }

    @Override
    public String toString() {
        StringBuilder risultato = new StringBuilder();
        risultato.append(this.nome);
        risultato.append("\nUscite: ");
        for (String direzione : this.getDirezioni()) {
            risultato.append(" " + direzione);
        }
        risultato.append("\nAttrezzi nella stanza: ");
        for (Attrezzo attrezzo : this.getAttrezzi()) {
            risultato.append(attrezzo.toString() + " ");
        }
        return risultato.toString();
    }

    public String getNome() {
        return this.nome;
    }
}