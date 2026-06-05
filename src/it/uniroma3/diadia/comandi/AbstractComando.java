package it.uniroma3.diadia.comandi;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando {
    
    private IO io;
    private String parametro;
    
 
    private static List<String> comandiDisponibili = new ArrayList<>();

    
    public AbstractComando() {
       
        if (comandiDisponibili.isEmpty()) {
            File cartellaComandi = new File("src/it/uniroma3/diadia/comandi");
            
            if (cartellaComandi.exists()) {
                for (File file : cartellaComandi.listFiles()) {
                    String nomeFile = file.getName();
                    
                    if (nomeFile.startsWith("Comando") && nomeFile.endsWith(".java")) {
                       
                        String nomeComando = nomeFile.substring(7, nomeFile.length() - 5).toLowerCase();
                        
                      
                        if (!nomeComando.equals("nonvalido")) {
                            comandiDisponibili.add(nomeComando);
                        }
                    }
                }
            }
        }
    }

   
    public static List<String> getComandiDisponibili() {
        return comandiDisponibili;
    }

    @Override
    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    @Override
    public String getParametro() {
        return this.parametro;
    }

    @Override
    public void setIo(IO io) {
        this.io = io;
    }

    @Override
    public IO getIo() {
        return this.io;
    }

    public abstract void esegui(Partita partita);
    
    @Override
    public abstract String getNome();
}