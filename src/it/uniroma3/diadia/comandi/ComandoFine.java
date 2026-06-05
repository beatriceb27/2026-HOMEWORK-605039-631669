package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public class ComandoFine extends AbstractComando{
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio("Grazie di aver giocato!"); 
		partita.setFinita();
	}
	
	@Override
    public String getNome() {
		return "fine";
	}
}
