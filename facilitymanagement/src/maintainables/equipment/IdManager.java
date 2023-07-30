package maintainables.equipment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * Die Klasse IdManager verwaltet die eindeutigen IDs für Equipment-Objekte.
 * Sie liest die aktuelle ID beim Programmstart aus einer Datei und aktualisiert diese Datei, 
 * wenn eine neue ID generiert wird.
 * 
 * @author Alexander Ansorge
 */
public class IdManager {

    private static final String ID_FILE = "equipment.txt";
    private static int currentId = 0;

    /*
     * Der statische Initialisierungsblock wird ausgeführt, sobald die Klasse geladen wird.
     * Er liest die aktuelle ID aus der ID_FILE.
     */
    static {
        try {
            File file = new File(ID_FILE);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                if (scanner.hasNextInt()) {
                    currentId = scanner.nextInt();
                }
                scanner.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Generiert eine neue eindeutige ID.
     * 
     * @return Die generierte ID als String.
     */
    public static String generateNewId() {
        currentId++;
        try {
            FileWriter fileWriter = new FileWriter(ID_FILE);
            fileWriter.write(String.valueOf(currentId));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(currentId);
    }
}
