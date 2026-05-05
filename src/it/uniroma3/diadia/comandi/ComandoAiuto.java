package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public class ComandoAiuto implements Comando{
	static final private String[] elencoComandi = {"vai", "aiuto", "fine","posa", "prendi", "guarda",};
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			this.io.mostraMessaggio(elencoComandi[i]+" ");
		System.out.println();
	}
	
	@Override
    public void setParametro(String parametro) {
		
	}
	
	@Override
    public String getNome() {
		return "Aiuto";
	}
	
	@Override
    public String getParametro() {
		return null;
	}
	
	@Override
    public void setIo(IO io) {
		this.io = io;
	}
    
	
}
