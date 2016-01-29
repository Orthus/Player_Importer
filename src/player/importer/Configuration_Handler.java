/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.importer;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Orthus
 */
public class Configuration_Handler {
    public String Results_path;
    public String Players_path;
    public Configuration_Handler(){
        try {
            FileReader reader = new FileReader("config.cfg");
            Properties props = new Properties();
            props.load(reader);
                
            Results_path = props.getProperty("Results_File");
            Players_path = props.getProperty("Players_File");
        }
        catch (IOException ex) {
            Logger.getLogger(PlayerImporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
