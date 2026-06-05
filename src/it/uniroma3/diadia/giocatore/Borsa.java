package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.Configurazione;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
    public final static int DEFAULT_PESO_MAX_BORSA = 10;
    private Map<String, Attrezzo> attrezzi;
    private int pesoMax;

    public Borsa() {
        this(DEFAULT_PESO_MAX_BORSA);
    }

    public Borsa(int pesoMax) {
    	this.pesoMax = Configurazione.getPesoMax();
    	this.attrezzi = new HashMap<>(); // Addio array!
    }

    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax()) {
            return false;
        }
        this.attrezzi.put(attrezzo.getNome(), attrezzo);
        return true;
    }

    public int getPesoMax() {
        return pesoMax;
    }

    public Attrezzo getAttrezzo(String nomeAttrezzo) {
        return this.attrezzi.get(nomeAttrezzo);
    }

    public int getPeso() {
        int pesoTotale = 0;
        for (Attrezzo a : this.attrezzi.values()) {
            pesoTotale += a.getPeso();
        }
        return pesoTotale;
    }

    public boolean isEmpty() {
        return this.attrezzi.isEmpty();
    }

    public boolean hasAttrezzo(String nomeAttrezzo) {
        return this.attrezzi.containsKey(nomeAttrezzo);
    }

    public Attrezzo removeAttrezzo(String nomeAttrezzo) {
        return this.attrezzi.remove(nomeAttrezzo);
    }
    
    public List<Attrezzo> getContenutoOrdinatoPerPeso() {
        List<Attrezzo> risultato = new ArrayList<>(this.attrezzi.values());
        
        Collections.sort(risultato, new Comparator<Attrezzo>() {
            @Override
            public int compare(Attrezzo a1, Attrezzo a2) {
                int cmp = a1.getPeso() - a2.getPeso();
                if (cmp == 0) {
                    cmp = a1.getNome().compareTo(a2.getNome());
                }
                return cmp;
            }
        });
        
        return risultato;
    }
    
    public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
        SortedSet<Attrezzo> risultato = new TreeSet<>(new Comparator<Attrezzo>() {
            @Override
            public int compare(Attrezzo a1, Attrezzo a2) {
                return a1.getNome().compareTo(a2.getNome());
            }
        });
        
        risultato.addAll(this.attrezzi.values());
        return risultato;
    }
    
    public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
        Map<Integer, Set<Attrezzo>> mappa = new HashMap<>();
        
        for (Attrezzo a : this.attrezzi.values()) {
            if (mappa.containsKey(a.getPeso())) {
                mappa.get(a.getPeso()).add(a);
            } else {
                Set<Attrezzo> s = new HashSet<>();
                s.add(a);
                mappa.put(a.getPeso(), s);
            }
        }
        return mappa;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        if (!this.isEmpty()) {
            s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
            
            List<Attrezzo> listaOrdinata = this.getContenutoOrdinatoPerPeso();
            for (Attrezzo a : listaOrdinata) {
                s.append(a.toString() + " ");
            }
        } else {
            s.append("Borsa vuota");
        }
        return s.toString();
    }
}