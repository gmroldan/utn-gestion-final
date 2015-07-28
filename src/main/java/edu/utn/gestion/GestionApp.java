package edu.utn.gestion;

import edu.utn.gestion.config.HibernateUtil;
import org.apache.log4j.Logger;

public class GestionApp {
    private static final Logger LOGGER = Logger.getLogger(GestionApp.class);
    
    /**
        * @param args the command line arguments
        */
    public static void main(String[] args) {
        HibernateUtil.init();
    }
}
