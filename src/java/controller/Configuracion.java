/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mary
 */
public class Configuracion {

    public static Properties properties = null;

    public static String getProperty(String key) {

        if (properties == null) {
            try {
                Configuracion.properties = new Properties();
                properties.load(Configuracion.class.getClassLoader().getResourceAsStream("properties/configuration.properties"));
            } catch (IOException ex) {
                Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Configuracion.properties.getProperty(key);
    }
}
