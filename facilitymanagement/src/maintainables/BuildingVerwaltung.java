package maintainables;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

/* BuildingVerwaltung ist die Verwaltungsklasse aller Gebäude.
 * Zuständig für Persistenz aller Maintainables.
 * 
 * @author Florian Schmidt
 */
public class BuildingVerwaltung implements Serializable {
    private static ArrayList<Building> buildings;

    /*
     * Der Konstruktor der BuildingVerwaltung erzeugt eine neue Liste von
     * Buidlings.
     * Wenn es bereits eine Liste von Buildings gibt, wird diese geladen.
     */
    public BuildingVerwaltung() {
        buildings = new ArrayList<>();
        try {
            loadBuildings();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Gibt die Liste von Buildings zurück.
     * 
     * @return Die Liste von Buildings.
     */
    public ArrayList<Building> getBuildings() {
        loadBuildings();
        return buildings;
    }

    /*
     * Hinzufügen eines Buildings zu der Gesamtheit aller Buildings.
     * Dabei wird dem übergebenen Building eine ID zugewiesen.
     * Die Änderung wird abgespeichert.
     * 
     * @param building Das Building, das hinzugefügt werden soll.
     */
    public void addBuilding(Building building) {
        building.setId(UUID.randomUUID());
        buildings.add(building);
        saveBuildings();
    }

    /*
     * Entfernen eines Buildings aus der Gesamtheit aller Buildings.
     * Die Änderung wird abgespeichert.
     * 
     * @param building Das Building, das entfernt werden soll.
     */
    public void removeBuilding(Building building) {
        buildings.remove(building);
        saveBuildings();
    }

    /*
     * Gibt das Building mit der übergebenen ID zurück.
     * 
     * @param id Die ID des gesuchten Buildings.
     * 
     * @return Das Building mit der übergebenen ID.
     */
    public static Building getBuildingById(int id) {
        loadBuildings();
        return buildings.get(id);
    }

    /*
     * Sortiert die Liste der Buildings nach ihrem Namen.
     * 
     * @return Eine sortierte Liste der Buildings nach ihrem Namen.
     */
    public ArrayList<Building> sortBuildingsByName() {
        loadBuildings();
        ArrayList<Building> sortedBuildings = buildings;
        Collections.sort(sortedBuildings);
        return sortedBuildings;
    }

    /*
     * Die Liste von Buildings wird abgespeichert in der buildings.txt Datei.
     * Sollte ein Fehler auftreten, wird dieser ausgegeben.
     * 
     * Diese Methode ist statisch, damit sie bei jeder Änderung der Liste aufgerufen
     * werden kann, ohne dass mehrere Objekte der Klasse BuildingVerwaltung
     * existieren müssen.
     * 
     * Der Speichervorgang wird so zum Teil der Anwendungslogik und muss nicht über
     * das GUI angestoßen werden.
     */
    public static void saveBuildings() {
        try {
            FileOutputStream fos = new FileOutputStream("buildings.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(buildings);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Die Liste von Buildings wird aus der buildings.txt Datei geladen.
     * Sollte ein Fehler auftreten, wird dieser ausgegeben.
     * 
     * Der Ladevorgang wird beim Programmstart bzw. beim Erzeugen eines Objekts der
     * BuildingVerwaltung in der GlobalVerwaltung angestoßen. Somit werden beim
     * Programmstart immer alle verfügbaren Daten geladen.
     */
    public static void loadBuildings() {
        File file = new File("buildings.txt");
        if (!file.exists()) {
            return;
        }

        try {
            FileInputStream fileInputStream = new FileInputStream("buildings.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            buildings = (ArrayList<Building>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception i) {
            i.printStackTrace();
        }

    }

}
