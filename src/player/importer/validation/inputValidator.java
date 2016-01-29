/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.importer.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerConfigurationException;
import player.importer.xml.XML_Handler;

/**
 *
 * @author Orthus
 */
public class inputValidator{

    public void Tester(String playerdata) {
            String Input_ID = null, Input_FirstName = null, Input_LastName = null, Input_DOB = null;
            List<String> playerinfo = Arrays.asList(playerdata.split(","));
            // validate player ID
            try {
                int test = Integer.parseInt(playerinfo.get(0));
                Input_ID = playerinfo.get(0);
            }
            catch(NumberFormatException e){
                System.out.println("Player ID is not a valid number!!!!");
            }
            //
            Input_FirstName= playerinfo.get(1);
            Input_LastName = playerinfo.get(2);
            //
            // validate player DOB
            SimpleDateFormat test = new SimpleDateFormat("MM/dd/yyyy");
            try {
                test.parse(playerinfo.get(3));
                Input_DOB = playerinfo.get(3);
            }
            catch(Exception e){
                            System.out.println("Player's DOB is invalid!!!!");

            }
            XML_Handler Handler = new XML_Handler();
            Boolean exists = Handler.Duplicate_checker(Input_ID);
            if (exists = false){
                try {
                    Handler.XML_Writer(Input_ID, Input_FirstName, Input_LastName, Input_DOB);
                } catch (TransformerConfigurationException ex) {
                    Logger.getLogger(inputValidator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
    }
    
}
