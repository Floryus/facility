package maintainables;

import java.util.ArrayList;

import enums.RoomTypeEnum;
import global.GlobalVerwaltung;

/*
 * Die Klasse Room repräsentiert einen Raum.
 * Sie implementiert das Maintainable-Interface, um für Todos eine einheltiche Implementierung mit Maintainables zu ermöglichen.
 * 
 * @Author Florian Schmidt
 */
public class Room extends Maintainable implements Comparable<Room> {
    String name;
    Level level;
    ArrayList<Equipment> equipment;
    RoomTypeEnum roomType;

    /*
     * Der Konstruktor der Room-Klasse erzeugt einen neuen Raum.
     * 
     * @param name Der Name des Raumes.
     * 
     * @param level Die Etage, in der sich der Raum befindet.
     */
    public Room(String name, Level level, RoomTypeEnum roomType) {
        this.name = name;
        this.level = level;
        this.roomType = roomType;
        this.equipment = new ArrayList<>();
    }

    /*
     * Fügt ein Equipment einem Raum hinzu.
     * Speichert diese Zuweisung in der BuildingVerwaltung.
     * 
     * @param equipment Das Equipment, das hinzugefügt werden soll.
     */
    public void addEquipment(Equipment equipment) {
        this.equipment.add(equipment);
        GlobalVerwaltung.getBuildingVerwaltung().saveBuildings();

    }

    /*
     * Entfernt ein Equipment von einem Raum.
     * Speichert diese Änderung in der BuildingVerwaltung.
     * 
     * @param equipment Das Equipment, das entfernt werden soll.
     */
    public void removeEquipment(Equipment equipment) {
        this.equipment.remove(equipment);
        GlobalVerwaltung.getBuildingVerwaltung().saveBuildings();

    }

    /*
     * Gibt die Liste von Equipment zurück.
     * 
     * @return Die Liste von Equipment.
     */
    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }

    /*
     * Gibt den Namen des Raumes zurück.
     * 
     * @return Der Name des Raumes.
     */
    public String getName() {
        return name;
    }

    public RoomTypeEnum getRoomType() {
        return roomType;
    }

    /*
     * Erzeugt einen String um den Mitarbeitern eine eindeutige Angabe über die Lage
     * zu geben.
     * 
     * @return Die Lage des Raumes.
     */
    @Override
    public String getLocation() {
        return level.getLocation() + ", Room " + name;
    }

    /*
     * Vergleicht zwei Räume anhand ihres Namens.
     * 
     * @param room Der Raum, mit dem verglichen werden soll.
     * 
     * @return 0, wenn die Namen gleich sind, -1, wenn der Raum vor dem übergebenen
     * Raum alphabetisch kommt, 1, wenn der Raum nach dem übergebenen Raum
     * alphabetisch kommt.
     */
    @Override
    public int compareTo(Room room) {
        return name.compareTo(room.name);
    }

    /*
     * Erzeugt einen String, der den Namen und die Lage des Raumes enthält.
     * 
     * @return Der Name und die Lage des Raumes.
     */
    @Override
    public String toString() {
        return ("Raum: " + name + " (" + getRoomType() + ") ");
    }
}
