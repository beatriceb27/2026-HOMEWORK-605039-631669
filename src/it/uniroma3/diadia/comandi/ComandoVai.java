package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.IO;

public class ComandoVai implements Comando{
	private String direzione;
	private IO io;
	
	
	@Override
	public void esegui(Partita partita) {
		if(direzione==null)
			this.io.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			this.io.mostraMessaggio("Direzione inesistente");
		else {
			partita.setStanzaCorrente(prossimaStanza);
			int cfu = partita.getCfu();
			partita.setCfu(cfu-1);
		}
		this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}
	
	@Override
    public void setParametro(String parametro) {
    	this.direzione = parametro;
	}
	@Override
    public String getNome() {
		return "vai";
	}
	
	@Override
    public String getParametro() {
		return this.direzione;
	}
	
	@Override
    public void setIo(IO io) {
		this.io = io;
	}
    
	
}
