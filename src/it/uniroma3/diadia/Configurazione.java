package it.uniroma3.diadia;

import java.io.IOException;
import java.util.Properties;

public class Configurazione {

    private static final String NOME_FILE = "diadia.properties";
    private static final String CFU = "cfu_iniziali";
    private static final String PESO_MAX = "peso_max_borsa";
    
    private static Properties prop = null;

    public static int getCFU() {
        if(prop == null) carica();
        return Integer.parseInt(prop.getProperty(CFU));
    }

    public static int getPesoMax() {
        if(prop == null) carica();
        return Integer.parseInt(prop.getProperty(PESO_MAX));
    }

    private static void carica() {
        prop = new Properties();
        try {
            prop.load(Configurazione.class.getClassLoader().getResourceAsStream(NOME_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}