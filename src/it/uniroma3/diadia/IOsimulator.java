package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IOsimulator implements IO {

    private List<String> righeLette;
    private int indiceRigheLette;
    
    private List<String> messaggiProdotti;
    private int indiceMessaggiMostrati;

    public IOsimulator(List<String> righeLette) {
        this.righeLette = righeLette;
        this.indiceRigheLette = 0;
        this.messaggiProdotti = new ArrayList<>();
        this.indiceMessaggiMostrati = 0;
    }

    public IOsimulator(String... righeLette) {
        this.righeLette = Arrays.asList(righeLette);
        this.indiceRigheLette = 0;
        this.messaggiProdotti = new ArrayList<>();
        this.indiceMessaggiMostrati = 0;
    }

    @Override
    public String leggiRiga() {
        if (this.indiceRigheLette < this.righeLette.size()) {
            String riga = this.righeLette.get(this.indiceRigheLette);
            this.indiceRigheLette++;
            return riga;
        } else {
            return null;
        }
    }

    @Override
    public void mostraMessaggio(String messaggio) {
        this.messaggiProdotti.add(messaggio);
    }

    public boolean hasNextMessaggio() {
        return this.indiceMessaggiMostrati < this.messaggiProdotti.size();
    }

    public String nextMessaggio() {
        String next = this.messaggiProdotti.get(this.indiceMessaggiMostrati);
        this.indiceMessaggiMostrati++;
        return next;
    }
}