package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando{
	
	@Override
    public void esegui(Partita partita) {
        String direzione = this.getParametro();
        
        if (direzione == null) {
            this.getIo().mostraMessaggio("Dove vuoi andare ?");
            return; 
        }
        
        Stanza prossimaStanza = null;
        try {
            Direzione dirEnum = Direzione.valueOf(direzione.toUpperCase());
            prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(dirEnum);
        } catch (IllegalArgumentException e) {
            prossimaStanza = null;
        }
        
        if (prossimaStanza == null) {
            this.getIo().mostraMessaggio("Direzione inesistente");
        } else {
            partita.setStanzaCorrente(prossimaStanza);
            int cfu = partita.getCfu(); 
            partita.setCfu(cfu - 1);
        }
        
        this.getIo().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
    }


	@Override
	public String getNome() {
		return "vai";
	}

}
