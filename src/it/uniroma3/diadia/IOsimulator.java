package it.uniroma3.diadia;

public class IOsimulator implements IO {

    private String[] righeDaLeggere; 
    private int indiceRigheLette;
    
    private String[] messaggiProdotti; 
    private int indiceMessaggiProdotti;
    private int indiceMessaggiLetti; 
    
    public IOsimulator(String[] righeDaLeggere) {
        this.righeDaLeggere = righeDaLeggere;
        this.indiceRigheLette = 0;
        
        this.messaggiProdotti = new String[100]; 
        this.indiceMessaggiProdotti = 0;
        this.indiceMessaggiLetti = 0;
    }

    @Override
    public String leggiRiga() {
        if (this.indiceRigheLette < this.righeDaLeggere.length) {
            String riga = this.righeDaLeggere[this.indiceRigheLette];
            this.indiceRigheLette++;
            return riga;
        }
        return null;
    }

    @Override
    public void mostraMessaggio(String messaggio) {
        if (this.indiceMessaggiProdotti < this.messaggiProdotti.length) {
            this.messaggiProdotti[this.indiceMessaggiProdotti] = messaggio;
            this.indiceMessaggiProdotti++;
        }
    }

    public String nextMessaggio() {
        String next = this.messaggiProdotti[this.indiceMessaggiLetti];
        this.indiceMessaggiLetti++;
        return next;
    }

    public boolean hasNextMessaggio() {
        return this.indiceMessaggiLetti < this.indiceMessaggiProdotti;
    }
}