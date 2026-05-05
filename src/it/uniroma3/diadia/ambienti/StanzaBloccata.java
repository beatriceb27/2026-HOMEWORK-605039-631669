package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {

    private String direzioneBloccata;
    private String attrezzoSbloccante;

    public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
        super(nome);
        this.direzioneBloccata = direzioneBloccata;
        this.attrezzoSbloccante = attrezzoSbloccante;
    }

    @Override
    public Stanza getStanzaAdiacente(String direzione) {
        if (direzione.equals(this.direzioneBloccata) && !this.hasAttrezzo(this.attrezzoSbloccante)) {
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