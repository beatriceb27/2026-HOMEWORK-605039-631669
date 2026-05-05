package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {

    private String attrezzoLuminoso;

    public StanzaBuia(String nome, String attrezzoLuminoso) {
        super(nome); // Chiama il costruttore della classe padre (Stanza)
        this.attrezzoLuminoso = attrezzoLuminoso;
    }

    @Override
    public String getDescrizione() {
        if (!this.hasAttrezzo(attrezzoLuminoso)) {
            return "qui c'è un buio pesto"; 
        }
        
        return super.getDescrizione();
    }
}