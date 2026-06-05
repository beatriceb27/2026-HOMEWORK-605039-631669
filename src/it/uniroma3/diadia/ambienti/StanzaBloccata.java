package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {

    private String direzioneBloccata;
    private String attrezzoSbloccante;

    public StanzaBloccata(String nome, Direzione direzione, String nomeAttrezzo) {
        super(nome);
        this.direzioneBloccata = direzione.name(); 
        this.attrezzoSbloccante = nomeAttrezzo;
    }

    @Override
    public Stanza getStanzaAdiacente(Direzione direzione) {
        if (direzione.name().equalsIgnoreCase(this.direzioneBloccata) && !this.hasAttrezzo(this.attrezzoSbloccante)) {
            return this;
        }
        
        return super.getStanzaAdiacente(direzione);
    }

    @Override
    public String getDescrizione() {
        String blocco = "Stanza bloccata a " + this.direzioneBloccata + ". Ti serve un " + this.attrezzoSbloccante + " per passare.\n";
        
        if (!this.hasAttrezzo(this.attrezzoSbloccante)) {
            return blocco + super.getDescrizione();
        }
        return super.getDescrizione();
    }
}