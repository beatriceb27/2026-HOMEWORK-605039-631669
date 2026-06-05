package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Personaggi.AbstractPersonaggio;
public class Stanza {

    private String nome;
    private Map<String, Attrezzo> attrezzi;
    private Map<Direzione, Stanza> stanzeAdiacenti;
    private AbstractPersonaggio personaggio;

    public Stanza(String nome) {
        this.nome = nome;
        this.attrezzi = new HashMap<>();
        this.stanzeAdiacenti = new HashMap<>();
    }
   
    public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
        this.stanzeAdiacenti.put(direzione, stanza);
    }

    public Stanza getStanzaAdiacente(Direzione direzione) {
        return this.stanzeAdiacenti.get(direzione);
    }

    public Set<Direzione> getDirezioni() {
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

 // Prima restituiva Collection<Attrezzo>
    public List<Attrezzo> getAttrezzi() {
        return new ArrayList<>(this.attrezzi.values());
    }

    public String getDescrizione() {
        return this.toString();
    }

    @Override
    public String toString() {
        StringBuilder risultato = new StringBuilder();
        risultato.append(this.nome);
        risultato.append("\nUscite: ");
        for (Direzione direzione : this.getDirezioni()) {
            risultato.append(" " + direzione.name().toLowerCase());
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
    
    public void setPersonaggio(AbstractPersonaggio personaggio) {
        this.personaggio = personaggio;
    }

    public AbstractPersonaggio getPersonaggio() {
        return this.personaggio;
    }
}