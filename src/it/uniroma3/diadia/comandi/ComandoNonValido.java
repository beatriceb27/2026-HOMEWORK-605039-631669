package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public class ComandoNonValido extends AbstractComando{
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("comando sconosciuto");
	}
	
	@Override
    public String getNome() {
		return null;
	}
}
