package player.importer;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.json.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
@author Orthus
*/
public class PlayerImporter {
    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("config.cfg");
            Properties props = new Properties();
            props.load(reader);
                
            String Results_path = props.getProperty("Results_File");
            String Players_path = props.getProperty("Players_File");
            System.out.println(Results_path + " and " + Players_path);
            
            String file = fileReader.readFile(Results_path);
            JSONObject obj = new JSONObject(file);
            JSONArray arr = obj.getJSONArray("Records");
                String playerdata = arr.getJSONObject(0).getString("Text");
                List<String> playerinfo = Arrays.asList(playerdata.split(","));
                String Input_ID = playerinfo.get(0);
                String Input_FirstName= playerinfo.get(1);
                String Input_LastName = playerinfo.get(2);
                String Input_DOB = playerinfo.get(3);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(Players_path);
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                String formattedDate = sdf.format(date);
                Element root = doc.getDocumentElement();
                NodeList playerlist = root.getElementsByTagName("player");
                if (playerlist.getLength() > 0){
                   int i = 0;
                   boolean exist = false;
                   int test = playerlist.getLength() -1;
                   while (i < playerlist.getLength()){
                    String id = playerlist.item(i).getAttributes().getNamedItem("userid").getNodeValue();
                    if (id.equals(Input_ID)){
                        exist = true;
                        i = playerlist.getLength();
                        System.out.println("Player already in system");
                    }
                    if (i == test && exist == false && !id.equals(Input_ID)){
                        Element newUser = doc.createElement("player");
                        newUser.setAttribute("userid", Input_ID);
                        Element firstname = doc.createElement("firstname");
                        firstname.appendChild(doc.createTextNode(Input_FirstName));
                        newUser.appendChild(firstname);
                        Element lastname = doc.createElement("lastname");
                        lastname.appendChild(doc.createTextNode(Input_LastName));
                        newUser.appendChild(lastname);
                        Element birthdate = doc.createElement("birthdate");
                        birthdate.appendChild(doc.createTextNode(Input_DOB));
                        newUser.appendChild(birthdate);
                        Element creationdate = doc.createElement("creationdate");
                        creationdate.appendChild(doc.createTextNode(formattedDate));
                        newUser.appendChild(creationdate);
                        Element lastmodifieddate = doc.createElement("lastmodifieddate");
                        lastmodifieddate.appendChild(doc.createTextNode(formattedDate));
                        newUser.appendChild(lastmodifieddate);
                        root.appendChild(newUser);
                        DOMSource source = new DOMSource(doc);
                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        transformer.setOutputProperty(OutputKeys.INDENT, "yes");                
                        StreamResult result = new StreamResult(Players_path);
                        transformer.transform(source, result);
                        exist = true;
                        System.out.println("Added to Player Database");
                    }
                    else {
                        i++;
                }}}
                else{
                    Element newUser = doc.createElement("player");
                        newUser.setAttribute("userid", Input_ID);
                        Element firstname = doc.createElement("firstname");
                        firstname.appendChild(doc.createTextNode(Input_FirstName));
                        newUser.appendChild(firstname);
                        Element lastname = doc.createElement("lastname");
                        lastname.appendChild(doc.createTextNode(Input_LastName));
                        newUser.appendChild(lastname);
                        Element birthdate = doc.createElement("birthdate");
                        birthdate.appendChild(doc.createTextNode(Input_DOB));
                        newUser.appendChild(birthdate);
                        Element creationdate = doc.createElement("creationdate");
                        creationdate.appendChild(doc.createTextNode(formattedDate));
                        newUser.appendChild(creationdate);
                        Element lastmodifieddate = doc.createElement("lastmodifieddate");
                        lastmodifieddate.appendChild(doc.createTextNode(formattedDate));
                        newUser.appendChild(lastmodifieddate);
                        root.appendChild(newUser);
                        DOMSource source = new DOMSource(doc);
                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        transformer.setOutputProperty(OutputKeys.INDENT, "yes");                
                        StreamResult result = new StreamResult(Players_path);
                        transformer.transform(source, result);
                }
        }catch (JSONException | ParserConfigurationException | TransformerException | IOException | SAXException ex) {
            Logger.getLogger(PlayerImporter.class.getName()).log(Level.SEVERE, null, ex);
}}}