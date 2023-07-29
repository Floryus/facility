package maintainables.equipment;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;
import maintainables.Equipment;

/*
 * Die EquipmentVerwaltung ist die Klasse, die alle Equipments verwaltet.
 * Sie ist zuständig für die Persistenz aller Equipments.
 * 
 * @author: Alexander Ansorge
 */
public class EquipmentVerwaltung implements Serializable {
    private static ArrayList<Equipment> equipmentList;

    /*
     * Der Konstruktor der EquipmentVerwaltung erzeugt eine neue Liste von Equipments.
     * Wenn es bereits eine Liste von Equipments gibt, wird diese geladen.
     */
    public EquipmentVerwaltung() {
        equipmentList = new ArrayList<>();
        try {
            loadEquipment();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Hinzufügen eines Equipments zu der Gesamtheit aller Equipments.
     * Die Änderung wird abgespeichert.
     * 
     * @param equipment Das Equipment, das hinzugefügt werden soll.
     */
    public void addEquipment(Equipment equipment) {
        equipment.setId(UUID.randomUUID().toString());
        equipmentList.add(equipment);
        saveEquipment();
    }

    /*
     * Entfernen eines Equipments aus der Gesamtheit aller Equipments.
     * Die Änderung wird abgespeichert.
     * 
     * @param equipment Das Equipment, das entfernt werden soll.
     */
    public void removeEquipment(Equipment equipment) {
        equipmentList.remove(equipment);
        saveEquipment();
    }

    /*
     * Gibt die Liste von Equipments zurück.
     * 
     * @return Die Liste von Equipments.
     */
    public ArrayList<Equipment> getEquipment() {
        loadEquipment();
        return equipmentList;
    }

    /*
     * Gibt das Equipment mit der übergebenen ID zurück.
     * 
     * @param id Die ID des gesuchten Equipments.
     * 
     * @return Das Equipment mit der übergebenen ID.
     */
    public static Equipment getEquipmentById(String id) {
        loadEquipment();
        for (Equipment equip : equipmentList) {
            if (equip.getId().equals(id)) {
                return equip;
            }
        }
        return null;
    }

    /*
     * Die Liste von Equipments wird abgespeichert in der equipment.txt Datei.
     * Sollte ein Fehler auftreten, wird dieser ausgegeben.
     * 
     * Diese Methode ist statisch, damit sie bei jeder Änderung der Liste aufgerufen
     * werden kann, ohne dass mehrere Objekte der Klasse EquipmentVerwaltung
     * existieren müssen.
     * 
     * Der Speichervorgang wird so zum Teil der Anwendungslogik und muss nicht über
     * das GUI angestoßen werden.
     */
    public static void saveEquipment() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("equipment.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(equipmentList);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception i) {
            i.printStackTrace();
        }
    }

    /*
     * Die Liste von Equipments wird aus der equipment.txt Datei geladen.
     * Sollte ein Fehler auftreten, wird dieser ausgegeben.
     * 
     * Der Ladevorgang wird beim Programmstart bzw. beim Erzeugen eines Objekts der
     * EquipmentVerwaltung in der GlobalVerwaltung angestoßen. Somit werden beim
     * Programmstart immer alle verfügbaren Daten geladen.
     */
    public static void loadEquipment() {
        File file = new File("equipment.txt");
        if (!file.exists()) {
            return;
        }

        try {
            FileInputStream fileInputStream = new FileInputStream("equipment.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            equipmentList = (ArrayList<Equipment>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception i) {
            i.printStackTrace();
        }
    }
}
