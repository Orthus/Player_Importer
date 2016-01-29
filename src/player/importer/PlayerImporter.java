package player.importer;
import player.importer.json.JsonReader;

/**
@author Orthus
*/
public class PlayerImporter {
    public static void main(String[] args) {
            Configuration_Handler config = new Configuration_Handler();
            String Results_path = config.Results_path;
            String Players_path = config.Players_path;
            System.out.println(Results_path + " and " + Players_path);
            JsonReader Json_Reader = new JsonReader();
            Json_Reader.Reader(Results_path);
    }
}